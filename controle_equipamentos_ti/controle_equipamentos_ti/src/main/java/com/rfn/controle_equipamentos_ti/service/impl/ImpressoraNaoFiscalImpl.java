package com.rfn.controle_equipamentos_ti.service.impl;

import java.util.List;
import java.util.Optional;

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
    
    @Override
    public void saveImpressoraNaoFiscal (ImpressoraNaoFiscal impressoraNaoFiscal) { //cria e atualiza (insert e update)
       impressoraNaoFiscalRepository.save(impressoraNaoFiscal);
    }

    @Override
    public ImpressoraNaoFiscal getImpressoraNaoFiscalById(Long pk_impressora) { 
        Optional<ImpressoraNaoFiscal> optional = impressoraNaoFiscalRepository.findById(pk_impressora); //Optional Evita NullPointerException
        ImpressoraNaoFiscal impressoraNaoFiscal = null;
        if (optional.isPresent()) {
            impressoraNaoFiscal = optional.get();
        } else {
            throw new RuntimeException("Impressora não encontrada para o número de série " + pk_impressora);
        }
        return impressoraNaoFiscal;
    }

    @Override
    public void deleteImpressoraNaoFiscalById(Long pk_impressora) {
       impressoraNaoFiscalRepository.deleteById(pk_impressora);
    }
    
}
