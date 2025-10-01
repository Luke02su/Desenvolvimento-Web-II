package com.rfn.controle_equipamentos_ti.service;

import java.util.List;
import com.rfn.controle_equipamentos_ti.model.Computador;

public interface ComputadorService {
    List<Computador> getAllComputadores(); //Ideal não é acessar repository direto
    void saveComputador(Computador computador);
    Computador getComputadorById(Long pk_computador);
    void deleteComputadorById(Long pk_computador);
}