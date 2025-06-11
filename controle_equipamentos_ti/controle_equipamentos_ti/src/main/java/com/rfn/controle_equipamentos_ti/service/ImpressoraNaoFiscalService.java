package com.rfn.controle_equipamentos_ti.service;

import java.util.List;

import com.rfn.controle_equipamentos_ti.model.ImpressoraNaoFiscal;

public interface ImpressoraNaoFiscalService {
    List<ImpressoraNaoFiscal> getAllImpressorasNaoFiscais(); //Ideal não é acessar repository direto
    //void saveImpressoraNaoFiscal(ImpressoraNaoFiscal impressoraNaoFiscal);
    //ImpressoraNaoFiscalService getImpressoraNaoFiscalsById(Long pk_impressora);
   // void deleteImpressoraNaoFiscalsById(Long pk_impressora);
}
