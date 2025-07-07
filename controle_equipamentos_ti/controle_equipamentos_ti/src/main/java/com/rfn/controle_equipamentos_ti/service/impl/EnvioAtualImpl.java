package com.rfn.controle_equipamentos_ti.service.impl;

import com.rfn.controle_equipamentos_ti.model.EnvioAtual; // Importe sua classe EnvioAtual
import com.rfn.controle_equipamentos_ti.repository.EnvioAtualRepository; // Importe seu EnvioAtualRepository
import com.rfn.controle_equipamentos_ti.service.EnvioAtualService; // Importe sua interface EnvioAtualService

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvioAtualImpl implements EnvioAtualService { // A classe implementa EnvioAtualService
    
    @Autowired
    private EnvioAtualRepository envioAtualRepository; // Injeta EnvioAtualRepository
    
    @Override
    public List<EnvioAtual> getAllEnviosAtuais() { // Retorna uma lista de EnvioAtual
        return envioAtualRepository.findAll();
    }
    
    @Override
    public void saveEnvioAtual(EnvioAtual envioAtual) { // Salva ou atualiza um EnvioAtual
       envioAtualRepository.save(envioAtual);
    }

    @Override
    public EnvioAtual getEnvioAtualById(Long pk_envio) { // Busca um EnvioAtual por ID
        Optional<EnvioAtual> optional = envioAtualRepository.findById(pk_envio); // Usa EnvioAtualRepository e pk_envio
        EnvioAtual envioAtual = null;
        if (optional.isPresent()) {
            envioAtual = optional.get();
        } else {
            throw new RuntimeException("Envio não encontrado para o ID " + pk_envio); // Mensagem de erro atualizada
        }
        return envioAtual;
    }

    @Override
    public void deleteEnvioAtualById(Long pk_envio) { // Deleta um EnvioAtual por ID
       envioAtualRepository.deleteById(pk_envio);
    }
    
}