package com.pwii.fiis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pwii.fiis.model.Fii;
import com.pwii.fiis.service.FiiService;

@Controller //Define um controlador Spring MVC. Indica que a classe é um controlador MVC
public class FiiController {
    @Autowired //Injeta automaticamente a dependência do serviço
    private FiiService fiiService;

    @GetMapping("/fii") //Mapear requisição get
    public String index(Model model) { //listar 
        model.addAttribute("fiisList", fiiService.getAllFiis()); //model objeto que leva dados do backend para a view
        return "fii/index"; //mapeia requisições GET para o método index()
    }

    @GetMapping("/showNewFiiForm") //Mapeia requisiões HTTP para métodos
    public String showNewFiiForm(Model model) {
        Fii fii = new Fii();
        model.addAttribute("fii", fii);
        return "new_fii";
    }

    @PostMapping("/saveFii")  //Mapeia requisiões HTTP para métodos
    public String saveFii(@ModelAttribute("fii") Fii fii) {
        fiiService.saveFii(fii);
        return "redirect:/";
    }

    @GetMapping("/deleteFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Fii fii = fiiService.getFiiById(id);
        model.addAttribute("fii", fii);
        return "update_product";
    }

    @GetMapping("/deleteFii/{id}")
    public String deleteFii(@PathVariable(value = "id") long id) {
        fiiService.deleteFiiById(id);
        return "redirect:/";
    }
}

//integração da camada de serviço com as views (index, new_product, update_product)
