package com.rfn.controle_equipamentos_ti.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rfn.controle_equipamentos_ti.model.Descarte;
import com.rfn.controle_equipamentos_ti.repository.DescarteRepository;
import com.rfn.controle_equipamentos_ti.service.DescarteService;

@Service
public class DescarteServiceImpl implements DescarteService {
    
    @Autowired
    private DescarteRepository descarteRepository;

    @Override
    public List<Descarte> getAllDescartes() {
        return descarteRepository.findAll();
    }

    @Override
    public void saveDescarte (Descarte descarte) { //cria e atualiza (insert e update)
        descarteRepository.save(descarte);
    }
    
    @Override
    public Descarte getDescarteById(Long pk_descarte) { 
        Optional<Descarte> optional = descarteRepository.findById(pk_descarte); //Optional Evita NullPointerException
        Descarte descarte = null;
        if (optional.isPresent()) {
            descarte = optional.get();
        } else {
            throw new RuntimeException("Computador não encontrado para o número de série " + pk_descarte);
        }
        return descarte;
    }

    //@Override
    //public void deleteDescarteById(Long pk_descarte) {
    //    descarteRepository.deleteById(pk_descarte);
    //}
}
