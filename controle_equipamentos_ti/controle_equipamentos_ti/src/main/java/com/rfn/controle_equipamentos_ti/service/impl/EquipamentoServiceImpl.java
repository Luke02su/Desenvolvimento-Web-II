package com.rfn.controle_equipamentos_ti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rfn.controle_equipamentos_ti.model.Equipamento;
import com.rfn.controle_equipamentos_ti.repository.EquipamentoRepository;
import com.rfn.controle_equipamentos_ti.service.EquipamentoService;

@Service
public class EquipamentoServiceImpl implements EquipamentoService {
    
    @Autowired
    private EquipamentoRepository equipamentoRepository;
    
    @Override
    public void saveEquipamento (Equipamento equipamento) { //cria e atualiza (insert e update)
        equipamentoRepository.save(equipamento);
    }
}
