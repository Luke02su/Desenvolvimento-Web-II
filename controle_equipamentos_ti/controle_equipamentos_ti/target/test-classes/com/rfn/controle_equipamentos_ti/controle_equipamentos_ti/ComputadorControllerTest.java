package com.rfn.controle_equipamentos_ti.controle_equipamentos_ti;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
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

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

// IMPORTAÇÕES PARA AS SUAS CLASSES DE DOMÍNIO E SERVIÇO
import com.rfn.controle_equipamentos_ti.config.TestConfig;
import com.rfn.controle_equipamentos_ti.controller.ComputadorController;
import com.rfn.controle_equipamentos_ti.model.Computador;
import com.rfn.controle_equipamentos_ti.model.Equipamento;
import com.rfn.controle_equipamentos_ti.service.ComputadorService;

@WebMvcTest(ComputadorController.class)
@Import(TestConfig.class)
public class ComputadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComputadorService computadorService;

    @AfterEach
    void resetMocks() {
        reset(computadorService);
    }

    private List<Computador> testCreateComputadorList(){
        Equipamento equipamentoA = new Equipamento();
        equipamentoA.setPk_num_serie("SERIAL-COMP-001");
        equipamentoA.setPlaca("PLACA-ABC-001");
        equipamentoA.setTipo("Notebook");
        equipamentoA.setModelo("Dell XPS 15");
        equipamentoA.setLocalizacao_atual(101);
        equipamentoA.setEnviado("Não");

        Computador computadorA = new Computador();
        computadorA.setPk_computador(1L);
        computadorA.setEquipamento(equipamentoA);
        computadorA.setProcessador("Intel Core i7");
        computadorA.setMemoria("16GB DDR4");
        computadorA.setWindows("Windows 10 Pro");
        computadorA.setArmazenamento("512GB SSD");
        computadorA.setFormatacao("2024-03-01");
        computadorA.setManutencao("Verificação anual");

        return List.of(computadorA); // Apenas um item, como no exemplo original de Product
    }

    @Test
    @DisplayName("GET /computador - Listar sem autenticacao")
    void testIndexNotAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/computador"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    @DisplayName("GET /computador - Listar com usuario logado")
    void testIndexAuthenticatedUser() throws Exception {
        when(computadorService.getAllComputadores()).thenReturn(testCreateComputadorList());

        mockMvc.perform(get("/computador"))
                .andExpect(status().isOk())
                .andExpect(view().name("computador/index"))
                .andExpect(model().attributeExists("computadoresList"))
                .andExpect(content().string(containsString("Listagem de Computadores")))
                .andExpect(content().string(containsString("Dell XPS 15"))); // Verifica conteúdo específico
        
        verify(computadorService).getAllComputadores();
    }

    @Test
    @WithMockUser(username = "admin@rfn.com", authorities = { "Admin" })
    @DisplayName("GET /computador/new - Exibir formulario de criacao (Admin)")
    void testCreateFormAuthorizedAdmin() throws Exception {
        mockMvc.perform(get("/computador/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("computador/new"))
                .andExpect(model().attributeExists("computador"))
                .andExpect(content().string(containsString("Cadastrar Novo Computador"))); // Verifica conteúdo específico
    }

    @Test
    @WithMockUser(username = "usuario@rfn.com", authorities = { "User" })
    @DisplayName("GET /computador/new - Acesso negado ao formulario de criacao (Nao-Admin)")
    void testCreateFormNotAuthorizedUser() throws Exception {
        mockMvc.perform(get("/computador/new"))
                .andExpect(status().isForbidden()); // Simplesmente verifica 403 Forbidden
    }

    @Test
    @WithMockUser(username = "admin@rfn.com", authorities = { "Admin" })
    @DisplayName("POST /computador/save - Salvar valido (Admin)")
    void testSaveValidComputador() throws Exception {
        Equipamento novoEquipamento = new Equipamento();
        novoEquipamento.setPk_num_serie("NEW-SN-98765");
        novoEquipamento.setPlaca("NEW-PLACA-XYZ");
        novoEquipamento.setTipo("Servidor");
        novoEquipamento.setModelo("HP ProLiant ML350");
        novoEquipamento.setLocalizacao_atual(103);
        novoEquipamento.setEnviado("Não");

        Computador novoComputador = new Computador();
        novoComputador.setEquipamento(novoEquipamento);
        novoComputador.setProcessador("Intel Xeon");
        novoComputador.setMemoria("64GB ECC");
        novoComputador.setWindows("Windows Server 2019");
        novoComputador.setArmazenamento("2TB SAS");
        novoComputador.setFormatacao("2025-07-01");
        novoComputador.setManutencao("Primeira instalacao");

        when(computadorService.saveComputador(any(Computador.class))).thenReturn(novoComputador);

        mockMvc.perform(post("/computador/save")
                .with(csrf())
                .flashAttr("computador", novoComputador))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/computador"));

        verify(computadorService).saveComputador(any(Computador.class));
    }

    @Test
    @WithMockUser(username = "admin@rfn.com", authorities = { "Admin" })
    @DisplayName("POST /computador/save - Falha na validacao (Admin)")
    void testSaveComputadorValidationError() throws Exception {
        Computador computadorInvalido = new Computador();
        // Não setamos o equipamento para simular erro de validação se @NotNull/etc. for aplicado
        computadorInvalido.setProcessador(""); // Exemplo de campo que pode ser inválido
        // Adicione outras propriedades para que o objeto seja 'inválido' conforme suas regras de validação

        mockMvc.perform(post("/computador/save")
                .with(csrf())
                .flashAttr("computador", computadorInvalido))
                .andExpect(status().isOk())
                .andExpect(view().name("computador/new")) // Volta para a página de criação
                .andExpect(model().attributeHasErrors("computador")); // Verifica que há erros de validação

        verify(computadorService, never()).saveComputador(any(Computador.class));
    }
}