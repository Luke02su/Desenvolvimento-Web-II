package com.rfn.controle_equipamentos_ti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rfn.controle_equipamentos_ti.model.ImpressoraNaoFiscal;
import com.rfn.controle_equipamentos_ti.repository.ImpressoraNaoFiscalRepository;
import com.rfn.controle_equipamentos_ti.service.ImpressoraNaoFiscalService;

@Service
public class ImpressoraNaoFiscalImpl implements ImpressoraNaoFiscalService {
    
    @Autowired
    private ImpressoraNaoFiscalRepository impressoraNaoFiscalRepository;
    
    @Override
    public List<ImpressoraNaoFiscal> getAllImpressorasNaoFiscais() {
        return impressoraNaoFiscalRepository.findAll();
    }

    
    //@Override
    //public void saveImpressoraNaoFiscal (ImpressoraNaoFiscal impressoraNaoFiscal) { //cria e atualiza (insert e update)
       // impressoraNaoFiscalRepository.save(impressoraNaoFiscal);
    //}
    
}
