package com.rfn.controle_equipamentos_ti.service;

import java.util.List;

import com.rfn.controle_equipamentos_ti.model.Generico;

public interface GenericoService {
    List<Generico> getAllGenericos(); //Ideal não é acessar repository direto
    void saveGenerico(Generico generico);
    Generico getGenericoById(Long pk_equipamento_generico);
    void deleteGenericoById(Long pk_equipamento_generico);  
}


