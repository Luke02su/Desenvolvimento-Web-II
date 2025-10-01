package com.rfn.controle_equipamentos_ti.service;

import java.util.List;

import com.rfn.controle_equipamentos_ti.model.Equipamento;

public interface EquipamentoService {
   List<Equipamento> getAllEquipamentos(); //Ideal não é acessar repository direto
    void saveEquipamento(Equipamento Equipamento);
   // Equipamento getComputadorById(String fk_num_serie);
   // void deleteComputadorById(String fk_num_serie);
}
