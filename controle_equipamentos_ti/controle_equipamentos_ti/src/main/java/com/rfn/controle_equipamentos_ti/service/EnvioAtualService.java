package com.rfn.controle_equipamentos_ti.service;

import java.util.List;

import com.rfn.controle_equipamentos_ti.model.EnvioAtual;

public interface EnvioAtualService {
    List<EnvioAtual> getAllEnviosAtuais();
    void saveEnvioAtual(EnvioAtual envioAtual);
    EnvioAtual getEnvioAtualById(Long pk_envio);
    void deleteEnvioAtualById(Long pk_envio);
}
