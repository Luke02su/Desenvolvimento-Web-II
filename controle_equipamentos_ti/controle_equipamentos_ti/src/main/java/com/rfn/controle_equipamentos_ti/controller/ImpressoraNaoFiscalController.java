package com.rfn.controle_equipamentos_ti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rfn.controle_equipamentos_ti.service.ImpressoraNaoFiscalService;
import com.rfn.controle_equipamentos_ti.service.EquipamentoService;

@Controller
public class ImpressoraNaoFiscalController {

    @Autowired //Injeta automaticamente a dependência do serviço
    private ImpressoraNaoFiscalService impressoraNaoFiscalService;
    @Autowired //Injeta automaticamente a dependência do serviço
    private EquipamentoService equipamentoService;

    @GetMapping("/impressoraNaoFiscal") //Mapear requisição get
    public String index(Model model) { //listar 
        model.addAttribute("impressorasList", impressoraNaoFiscalService.getAllImpressorasNaoFiscais()); //model objeto que leva dados do backend para a view
        return "impressoraNaoFiscal/index"; //mapeia requisições GET para o método index()
    }
}
