package com.rfn.controle_equipamentos_ti.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rfn.controle_equipamentos_ti.model.Computador;
import com.rfn.controle_equipamentos_ti.repository.ComputadorRepository;
import com.rfn.controle_equipamentos_ti.service.ComputadorService;

@Service
public class ComputadorServiceImpl implements ComputadorService {

    @Autowired
    private ComputadorRepository computadorRepository;

    @Override
    public List<Computador> getAllComputadores() {
        return computadorRepository.findAllByOrderByPk_computadorDesc();
    }

    @Override
    public void saveComputador (Computador computador) { //cria e atualiza (insert e update)
        computadorRepository.save(computador);
    }
    
    @Override
    public Computador getComputadorById(Long pk_computador) { 
        Optional<Computador> optional = computadorRepository.findById(pk_computador); //Optional Evita NullPointerException
        Computador computador = null;
        if (optional.isPresent()) {
            computador = optional.get();
        } else {
            throw new RuntimeException("Computador não encontrado para o número de série " + pk_computador);
        }
        return computador;
    }

    @Override
    public void deleteComputadorById(Long pk_computador) {
       computadorRepository.deleteById(pk_computador);
    }
}

