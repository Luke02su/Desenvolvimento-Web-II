/*package com.rfn.controle_equipamentos_ti.controle_equipamentos_ti;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate; // Mantenha se você ainda usa LocalDate em algum mock de data, mesmo que a entidade seja Date
import java.util.List;
import java.util.Date; // Import para java.util.Date
import java.util.Calendar; // Import para criar java.util.Date de forma controlada

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

// IMPORTAÇÕES PARA AS SUAS CLASSES DE DOMÍNIO E SERVIÇO DE DESCARTE
import com.rfn.controle_equipamentos_ti.config.TestConfig;
import com.rfn.controle_equipamentos_ti.controller.DescarteController;
import com.rfn.controle_equipamentos_ti.model.Descarte;
import com.rfn.controle_equipamentos_ti.model.Equipamento;
import com.rfn.controle_equipamentos_ti.service.DescarteService;


@WebMvcTest(DescarteController.class)
@Import(TestConfig.class)
public class DescarteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DescarteService descarteService;

    @AfterEach
    void resetMocks() {
        reset(descarteService);
    }

    // Método auxiliar para criar uma lista de objetos Descarte para testes
    private List<Descarte> testCreateDescarteList(){
        Equipamento equipamentoDescartado = new Equipamento();
        equipamentoDescartado.setPk_num_serie("SERIAL-DESC-001");
        equipamentoDescartado.setPlaca("PLACA-ABC-001");
        equipamentoDescartado.setTipo("Notebook");
        equipamentoDescartado.setModelo("Dell XPS 15");
        equipamentoDescartado.setLocalizacao_atual(101);
        equipamentoDescartado.setEnviado("Descartado");

        Descarte descarteA = new Descarte();
        descarteA.setPk_descarte(1L);
        descarteA.setEquipamento(equipamentoDescartado);
        descarteA.setPlaca("PLACA-ABC-001");
        descarteA.setTipo("Notebook");
        descarteA.setModelo("Dell XPS 15");
        
        descarteA.setMotivo("Obsoleto / Fim de vida útil");
        descarteA.setUltima_localizacao(101);

        // Usando Calendar para criar java.util.Date para o atributo 'data'
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JULY, 10); 
        descarteA.setData(cal.getTime()); // Define a data como java.util.Date
        
        descarteA.setUsuario("admin.teste");

        return List.of(descarteA);
    }

    // --- TESTES DE LISTAGEM ---

    @Test
    @DisplayName("GET /descarte - Listar descartes sem autenticacao")
    void testIndexNotAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/descarte"))
                .andExpect(status().isUnauthorized()); // Espera 401 Unauthorized se nao for permitAll
    }

    @Test
    @WithMockUser
    @DisplayName("GET /descarte - Listar descartes com usuario logado")
    void testIndexAuthenticatedUser() throws Exception {
        when(descarteService.getAllDescartes()).thenReturn(testCreateDescarteList());

        mockMvc.perform(get("/descarte"))
                .andExpect(status().isOk())
                .andExpect(view().name("descarte/index"))
                .andExpect(model().attributeExists("descartesList"))
                .andExpect(content().string(containsString("Lista de descartes")))
                .andExpect(content().string(containsString("Dell XPS 15"))); // Verifica conteúdo específico
        
        verify(descarteService).getAllDescartes();
    }

    // --- TESTES DE SALVAR (SAVE) ---

    // Método auxiliar para criar um objeto Descarte válido para salvar/deletar
    // Este método é usado para criar dados mockados para os testes de POST.
    private Descarte createValidDescarteForAction() {
        Equipamento equipamentoParaAcao = new Equipamento();
        equipamentoParaAcao.setPk_num_serie("ACTION-SN-12345");
        equipamentoParaAcao.setPlaca("ACTION-PLACA");
        equipamentoParaAcao.setTipo("Servidor");
        equipamentoParaAcao.setModelo("HP ProLiant ML350 Gen10");
        equipamentoParaAcao.setLocalizacao_atual(106);
        equipamentoParaAcao.setEnviado("Em uso");

        Descarte descarteParaAcao = new Descarte();
        descarteParaAcao.setEquipamento(equipamentoParaAcao);
        descarteParaAcao.setPlaca(equipamentoParaAcao.getPlaca());
        descarteParaAcao.setTipo(equipamentoParaAcao.getTipo());
        descarteParaAcao.setModelo(equipamentoParaAcao.getModelo());
        descarteParaAcao.setMotivo("Substituição de hardware");
        descarteParaAcao.setUltima_localizacao(106);
        
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JULY, 10);
        descarteParaAcao.setData(cal.getTime());
        
        descarteParaAcao.setUsuario("action.user");
        return descarteParaAcao;
    }

    @Test
    @WithMockUser(username = "admin@rfn.com", authorities = { "Admin" })
    @DisplayName("POST /descarte/save - Salvar descarte valido (Admin)")
    void testSaveDescarteAsAdmin() throws Exception {
        Descarte descarteValido = createValidDescarteForAction();
        when(descarteService.saveDescarte(any(Descarte.class))).thenReturn(descarteValido);

        mockMvc.perform(post("/descarte/save")
                .with(csrf())
                .flashAttr("descarte", descarteValido))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/descarte"));

        verify(descarteService).saveDescarte(any(Descarte.class));
    }

    @Test
    @WithMockUser(username = "usuario@rfn.com", authorities = { "User" })
    @DisplayName("POST /descarte/save - Acesso negado salvar (Nao-Admin)")
    void testSaveDescarteAccessDenied() throws Exception {
        Descarte descarteParaNegar = createValidDescarteForAction();

        mockMvc.perform(post("/descarte/save")
                .with(csrf())
                .flashAttr("descarte", descarteParaNegar))
                .andExpect(status().isForbidden());

        verify(descarteService, never()).saveDescarte(any(Descarte.class));
    }

    // --- TESTES DE DELETAR ---

    @Test
    @WithMockUser(username = "admin@rfn.com", authorities = { "Admin" })
    @DisplayName("POST /descarte/delete/{id} - Deletar descarte (Admin)")
    void testDeleteDescarteAsAdmin() throws Exception {
        Long descarteId = 1L;

        // Se o método deleteDescarte no seu serviço for 'void', use Mockito.doNothing():
        // Mockito.doNothing().when(descarteService).deleteDescarte(eq(descarteId));

        mockMvc.perform(post("/descarte/delete/{id}", descarteId)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/descarte"));

        verify(descarteService).deleteDescarte(eq(descarteId));
    }

    @Test
    @WithMockUser(username = "usuario@rfn.com", authorities = { "User" })
    @DisplayName("POST /descarte/delete/{id} - Acesso negado deletar (Nao-Admin)")
    void testDeleteDescarteAccessDenied() throws Exception {
        Long descarteId = 1L;

        mockMvc.perform(post("/descarte/delete/{id}", descarteId)
                .with(csrf()))
                .andExpect(status().isForbidden());

        verify(descarteService, never()).deleteDescarte(any(Long.class));
    }
} */