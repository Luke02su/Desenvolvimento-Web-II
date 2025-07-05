package com.rfn.controle_equipamentos_ti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.rfn.controle_equipamentos_ti.model.Equipamento;
import com.rfn.controle_equipamentos_ti.service.EquipamentoService;

@Controller
public class EquipamentoController {

    @Autowired //Injeta automaticamente a dependência do serviço
    private EquipamentoService equipamentoService;

    @PostMapping("/equipamento/save")
    public String salvarEquipamento(@ModelAttribute @Validated Equipamento equipamento, BindingResult result) {
        if (result.hasErrors()) {
            return "computador/create";
        }

        // Agora salva o computador
        equipamentoService.saveEquipamento(equipamento); // ou via computadorService.saveComputador(computador)
        return "redirect:/computador";
        } 
    }


