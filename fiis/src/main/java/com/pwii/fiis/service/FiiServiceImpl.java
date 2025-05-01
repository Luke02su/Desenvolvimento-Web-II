package com.pwii.fiis.service;

import java.util.List;
import java.util.Optional;

import com.pwii.fiis.model.Fii;
import com.pwii.fiis.repository.FiiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Indica que a classe implementa lógica de serviço (camada de negócio)
public class FiiServiceImpl implements FiiService {
    @Autowired //Injeção automática de dependências (neste caso, o repositório)
    private FiiRepository fiiRepository;

    @Override //sobreposição
    public List<Fii> getAllFiis() {
        return fiiRepository.findAll(); // findAll vem do repository (devolve lista de produtos)
    }

    @Override
    public void saveFii(Fii fii) { //cria e atualiza (insert e update)
        fiiRepository.save(fii);
    }
    
    @Override
    public Fii getFiiById(long id) { 
        Optional<Fii> optional = fiiRepository.findById(id); //Optional Evita NullPointerException
        Fii product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException("Fii não encontrado para o id " + id);
        }
        return product;
    }

    @Override
    public void deleteFiiById(long id) {
        fiiRepository.deleteById(id);
    }
}

//Separação de responsabilidades (boas práticas de arquitetura)
