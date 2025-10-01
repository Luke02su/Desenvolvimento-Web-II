package com.rfn.controle_equipamentos_ti.service.impl;

import com.rfn.controle_equipamentos_ti.model.Loja; // Importe sua classe Loja
import com.rfn.controle_equipamentos_ti.repository.LojaRepository; // Importe seu LojaRepository
import com.rfn.controle_equipamentos_ti.service.LojaService; // Importe sua interface LojaService

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LojaImpl implements LojaService { // Altere para LojaService
    
    @Autowired
    private LojaRepository lojaRepository; // Altere para LojaRepository
    
    @Override
    public List<Loja> getAllLojas() { // Altere o nome do método e o tipo de retorno
        return lojaRepository.findAll();
    }
    
    @Override
    public void saveLoja(Loja loja) { // Altere o nome do método e o parâmetro
       lojaRepository.save(loja);
    }

    @Override
    public Loja getLojaById(Long pk_loja) { // Altere o nome do método e o parâmetro
        Optional<Loja> optional = lojaRepository.findById(pk_loja); // Altere para LojaRepository e pk_loja
        Loja loja = null;
        if (optional.isPresent()) {
            loja = optional.get();
        } else {
            throw new RuntimeException("Loja não encontrada para o ID " + pk_loja); // Mensagem de erro atualizada
        }
        return loja;
    }

    @Override
    public void deleteLojaById(Long pk_loja) { // Altere o nome do método e o parâmetro
       lojaRepository.deleteById(pk_loja);
    }
    
}