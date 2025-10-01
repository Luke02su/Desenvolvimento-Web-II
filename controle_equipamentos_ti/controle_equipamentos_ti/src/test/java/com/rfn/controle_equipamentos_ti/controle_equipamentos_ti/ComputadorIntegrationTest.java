package com.rfn.controle_equipamentos_ti.controle_equipamentos_ti;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.rfn.controle_equipamentos_ti.model.Computador;
import com.rfn.controle_equipamentos_ti.model.Equipamento;
import com.rfn.controle_equipamentos_ti.repository.ComputadorRepository;
import com.rfn.controle_equipamentos_ti.repository.EquipamentoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ComputadorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ComputadorRepository computadorRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Test
    @WithMockUser(authorities = { "Admin" })
    void testSaveComputadorIntegration() throws Exception {

        Equipamento equipamentoA = new Equipamento();
        equipamentoA.setPk_num_serie("DELL-SN-ABCDE12345");
        equipamentoA.setPlaca("ABCDEFGH");
        equipamentoA.setTipo("Notebook");
        equipamentoA.setModelo("Latitude 5420");
        equipamentoA.setLocalizacao_atual(999);
        equipamentoA.setEnviado("Não");

        equipamentoRepository.save(equipamentoA);

        Computador computadorA = new Computador();
        computadorA.setEquipamento(equipamentoA);
        computadorA.setProcessador("Intel Core i7-1185G7");
        computadorA.setMemoria("16GB DDR4");
        computadorA.setWindows("10 Pro");
        computadorA.setArmazenamento("512GB NVMe SSD");
        computadorA.setFormatacao("Última formatação em 01/07/2025");
        computadorA.setManutencao("Verificação de hardware e limpeza de software em 01/07/2025");

        mockMvc.perform(post("/computador/save")
                .with(csrf())
                .flashAttr("computador", computadorA))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/computador"));

        assertTrue(computadorRepository.findAll().stream()
                .anyMatch(c -> c.getEquipamento() != null &&
                                c.getEquipamento().getPk_num_serie().equals("DELL-SN-ABCDE12345")));

        assertTrue(computadorRepository.findAll().stream()
                .anyMatch(c -> c.getProcessador().equals("Intel Core i7-1185G7")));

        Computador savedComputador = computadorRepository.findAll().stream()
                                    .filter(c -> c.getEquipamento() != null &&
                                                 c.getEquipamento().getPk_num_serie().equals("DELL-SN-ABCDE12345"))
                                    .findFirst()
                                    .orElse(null);

        assertNotNull(savedComputador);
        assertNotNull(savedComputador.getPk_computador());
        assertNotNull(savedComputador.getEquipamento().getPk_num_serie());
    }
}