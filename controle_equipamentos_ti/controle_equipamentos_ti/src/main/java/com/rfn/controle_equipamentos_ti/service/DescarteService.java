package com.rfn.controle_equipamentos_ti.service;

import java.util.List;

import com.rfn.controle_equipamentos_ti.model.Descarte;

public interface DescarteService {
    List<Descarte> getAllDescartes(); //Ideal não é acessar repository direto
    void saveDescarte(Descarte descarte);
    Descarte getDescarteById(Long pk_descarte);
    //void deleteDescarteById(Long pk_descarte);
}
