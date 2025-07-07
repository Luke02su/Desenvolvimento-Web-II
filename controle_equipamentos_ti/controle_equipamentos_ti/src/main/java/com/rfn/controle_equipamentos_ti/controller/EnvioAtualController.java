package com.rfn.controle_equipamentos_ti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rfn.controle_equipamentos_ti.model.EnvioAtual; 
import com.rfn.controle_equipamentos_ti.model.Equipamento; 
import com.rfn.controle_equipamentos_ti.model.Loja;   
import com.rfn.controle_equipamentos_ti.service.EnvioAtualService; 
import com.rfn.controle_equipamentos_ti.service.EquipamentoService; 
import com.rfn.controle_equipamentos_ti.service.LojaService;

@Controller
public class EnvioAtualController {

    @Autowired
    private EnvioAtualService envioAtualService;
    @Autowired
    private EquipamentoService equipamentoService;
    @Autowired 
    private LojaService lojaService;

    @GetMapping("/envioAtual") // Mapeia requisições GET para listar os envios
    public String index(Model model) {
        model.addAttribute("enviosList", envioAtualService.getAllEnviosAtuais());
        return "envioAtual/index"; // Retorna o nome da view (provavelmente "envios/index.html")
    }

    @GetMapping("/envioAtual/create")
    public String create(Model model) {
        model.addAttribute("envioAtual", new EnvioAtual());
        model.addAttribute("equipamentos", equipamentoService.getAllEquipamentos()); 
        model.addAttribute("lojas", lojaService.getAllLojas());
        return "envioAtual/create";
    }

    @PostMapping("/envioAtual/save")
    public String salvarEnvioAtual(@ModelAttribute @Validated EnvioAtual envioAtual, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Se houver erros, adicione novamente as listas para preencher os dropdowns ao retornar à view
            model.addAttribute("equipamentos", equipamentoService.getAllEquipamentos());
            model.addAttribute("lojas", lojaService.getAllLojas());
            return "envioAtual/create";
        }
        
        envioAtualService.saveEnvioAtual(envioAtual);
        return "redirect:/envioAtual";
    }

    @GetMapping("/envioAtual/delete/{pk_envio}")
    public String deleteEnvioAtual(@PathVariable Long pk_envio) {
        this.envioAtualService.deleteEnvioAtualById(pk_envio);
        return "redirect:/envioAtual";
    }

    @GetMapping("/envioAtual/edit/{pk_envio}")
    public String edit(@PathVariable Long pk_envio, Model model) {
        EnvioAtual envioAtual = envioAtualService.getEnvioAtualById(pk_envio);
        model.addAttribute("envioAtual", envioAtual);
        // Adiciona listas de Equipamentos e Lojas para preencher dropdowns no formulário de edição
        model.addAttribute("equipamentos", equipamentoService.getAllEquipamentos());
        model.addAttribute("lojas", lojaService.getAllLojas());
        return "envioAtual/edit";
    }
}