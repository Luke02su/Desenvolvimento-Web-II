package com.rfn.controle_equipamentos_ti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
        equipamentoService.saveEquipamento(equipamento); // ou via computadorService.saveComputador(computador)
        return null;
    }

    @GetMapping("/equipamentos") // Mapeia a requisição GET para listar equipamentos
    public String index(Model model) { // Método para listar
        // Adiciona a lista de equipamentos ao modelo, buscando-os do serviço
        model.addAttribute("equipamentosList", equipamentoService.getAllEquipamentos()); 
        // Retorna o nome da view, que deve ser 'equipamentos/index.html'
        return "equipamentos/index"; 
    }
}


