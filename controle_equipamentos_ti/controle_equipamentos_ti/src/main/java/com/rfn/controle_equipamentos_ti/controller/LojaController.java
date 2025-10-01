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

import com.rfn.controle_equipamentos_ti.model.Loja;
import com.rfn.controle_equipamentos_ti.service.LojaService;

@Controller
public class LojaController {

    @Autowired
    private LojaService lojaService;
    
    @GetMapping("/loja")
    public String index(Model model) {
        model.addAttribute("lojasList", lojaService.getAllLojas());
        return "loja/index"; 
    }

    @GetMapping("/loja/create")
    public String create(Model model) {
        model.addAttribute("loja", new Loja());
        return "loja/create";
    }

    @PostMapping("/loja/save")
    public String salvarLoja(@ModelAttribute @Validated Loja loja, BindingResult result) {
        if (result.hasErrors()) {
            return "loja/create";
        }
        lojaService.saveLoja(loja);
        return "redirect:/loja";
    }

    @GetMapping("/loja/delete/{pk_loja}")
    public String deleteLoja(@PathVariable Long pk_loja) {
        this.lojaService.deleteLojaById(pk_loja);
        return "redirect:/loja";
    }

    @GetMapping("/loja/edit/{pk_loja}")
    public String edit(@PathVariable Long pk_loja, Model model) {
        Loja loja = lojaService.getLojaById(pk_loja);
        model.addAttribute("loja", loja);
        return "loja/edit";
    }
}