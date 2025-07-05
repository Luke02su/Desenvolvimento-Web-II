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

import com.rfn.controle_equipamentos_ti.service.ImpressoraNaoFiscalService;
import com.rfn.controle_equipamentos_ti.model.Equipamento;
import com.rfn.controle_equipamentos_ti.model.ImpressoraNaoFiscal;
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

    @GetMapping("/impressoraNaoFiscal/create") //Mapeia requisiões HTTP para métodos
    public String create(Model model) {
        model.addAttribute("impressoraNaoFiscal", new ImpressoraNaoFiscal());
        return "impressoraNaoFiscal/create";
    }

    @PostMapping("/impressoraNaoFiscal/save")
    public String salvarImpressoraNaoFiscal(@ModelAttribute @Validated ImpressoraNaoFiscal impressoraNaoFiscal, BindingResult result) {
        if (result.hasErrors()) {
            return "impressoraNaoFiscal/create";
        }

        // Salvar primeiro o equipamento associado
        Equipamento equipamento = impressoraNaoFiscal.getEquipamento();
        if (equipamento != null) {
            equipamentoService.saveEquipamento(equipamento); // ou via service
        }

        // Agora salva o computador
        impressoraNaoFiscalService.saveImpressoraNaoFiscal(impressoraNaoFiscal); // ou via computadorService.saveComputador(computador)
        return "redirect:/impressoraNaoFiscal";
    }

    @GetMapping("/impressoraNaoFiscal/delete/{pk_impressora}")
    public String deleteImpressoraNaoFiscal(@PathVariable Long pk_impressora) {
        this.impressoraNaoFiscalService.deleteImpressoraNaoFiscalById(pk_impressora);
        return "redirect:/impressoraNaoFiscal";
    }

    @GetMapping("/impressoraNaoFiscal/edit/{pk_impressora}")
    public String edit(@PathVariable Long pk_impressora, Model model) {
        ImpressoraNaoFiscal impressoraNaoFiscal = impressoraNaoFiscalService.getImpressoraNaoFiscalById(pk_impressora);
        model.addAttribute("impressoraNaoFiscal", impressoraNaoFiscal);
        return "impressoraNaoFiscal/edit";
    }
}
