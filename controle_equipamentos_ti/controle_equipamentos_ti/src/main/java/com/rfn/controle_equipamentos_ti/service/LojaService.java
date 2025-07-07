package com.rfn.controle_equipamentos_ti.service;

import java.util.List;

import com.rfn.controle_equipamentos_ti.model.Loja;

public interface LojaService {
    List<Loja> getAllLojas(); //Ideal não é acessar repository direto
    void saveLoja(Loja loja);
    Loja getLojaById(Long pk_loja);
    void deleteLojaById(Long pk_loja);
}
