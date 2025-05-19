package com.pwii.fiis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

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

    @GetMapping("/fii/create") //Mapeia requisiões HTTP para métodos
    public String create(Model model) {
        model.addAttribute("fii", new Fii());
        return "fii/create";
    }

    @PostMapping("/fii/save")  //Mapeia requisiões HTTP para métodos
    public String postMethodName(@ModelAttribute @Valid Fii fii, BindingResult result) {
        if (result.hasErrors()) {
            return "fii/create";
        }
        fiiService.saveFii(fii);
        return "redirect:/fii";
    }

    @GetMapping("/fii/delete/{id}")
    public String deleteFii(@PathVariable long id) {
        this.fiiService.deleteFiiById(id);
        return "redirect:/fii";
    }

    @GetMapping("/fii/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Fii fii = fiiService.getFiiById(id);
        model.addAttribute("fii", fii);
        return "fii/edit";
    }
}

//integração da camada de serviço com as views (index, new_product, update_product)
