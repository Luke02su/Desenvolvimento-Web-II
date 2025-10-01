package com.rfn.controle_equipamentos_ti.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rfn.controle_equipamentos_ti.model.Generico;
import com.rfn.controle_equipamentos_ti.repository.GenericoRepository;
import com.rfn.controle_equipamentos_ti.service.GenericoService;

@Service
public class GenericoServiceImpl implements GenericoService {
    @Autowired
    private GenericoRepository genericoRepository;
    
    @Override
    public List<Generico> getAllGenericos() {
        return genericoRepository.findAll();
    }
    
    @Override
    public void saveGenerico (Generico generico) { //cria e atualiza (insert e update)
       genericoRepository.save(generico);
    }

    @Override
    public Generico getGenericoById(Long pk_equipamento_generico) { 
        Optional<Generico> optional = genericoRepository.findById(pk_equipamento_generico); //Optional Evita NullPointerException
        Generico generico = null;
        if (optional.isPresent()) {
            generico = optional.get();
        } else {
            throw new RuntimeException("Equipamento genérico não encontrado para o número de série " + pk_equipamento_generico);
        }
        return generico;
    }

    @Override
    public void deleteGenericoById(Long pk_equipamento_generico) {
       genericoRepository.deleteById(pk_equipamento_generico);
    }
}
