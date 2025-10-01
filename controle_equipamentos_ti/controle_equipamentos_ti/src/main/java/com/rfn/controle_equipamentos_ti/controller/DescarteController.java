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

import com.rfn.controle_equipamentos_ti.model.Descarte;
import com.rfn.controle_equipamentos_ti.model.Equipamento;
import com.rfn.controle_equipamentos_ti.service.DescarteService;
import com.rfn.controle_equipamentos_ti.service.EquipamentoService;

@Controller
public class DescarteController {

    @Autowired //Injeta automaticamente a dependência do serviço
    private DescarteService descarteService;
    @Autowired //Injeta automaticamente a dependência do serviço
    private EquipamentoService equipamentoService;

    @GetMapping("/descarte") //Mapear requisição get
    public String index(Model model) { //listar 
        model.addAttribute("descartesList", descarteService.getAllDescartes()); //model objeto que leva dados do backend para a view
        return "descarte/index"; //mapeia requisições GET para o método index()
    }

    //@GetMapping("/descarte/create") //Mapeia requisiões HTTP para métodos
    //public String create(Model model) {
    //    model.addAttribute("computador", new Computador());
    //    return "computador/create";
    //}

    @PostMapping("/descarte/save")
    public String salvarDescarte(@ModelAttribute @Validated Descarte descarte, BindingResult result) {
        if (result.hasErrors()) {
            return "descarte/create";
        }

        // Salvar primeiro o equipamento associado
        Equipamento equipamento = descarte.getEquipamento();
        if (equipamento != null) {
           equipamentoService.saveEquipamento(equipamento); // ou via service
        }

        // Agora salva o computador
        descarteService.saveDescarte(descarte); // ou via computadorService.saveComputador(computador)
        return "redirect:/descarte";
    }


    //@GetMapping("/descarte/delete/{pk_equipamento_generico}")
    //public String deleteDescarte(@PathVariable Long pk_descarte) {
    //    this.descarteService.deleteDescarteById(pk_descarte);
    //    return "redirect:/descarte";
    //}

    @GetMapping("/descarte/edit/{pk_descarte}")
    public String edit(@PathVariable Long pk_descarte, Model model) {
        Descarte descarte = descarteService.getDescarteById(pk_descarte);
        model.addAttribute("descarte", descarte);
        return "descarte/edit";
    }
}