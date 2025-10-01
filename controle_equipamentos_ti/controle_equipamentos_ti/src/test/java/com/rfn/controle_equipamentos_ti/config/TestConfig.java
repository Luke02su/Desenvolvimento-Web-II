package com.rfn.controle_equipamentos_ti.config;

import com.rfn.controle_equipamentos_ti.service.ComputadorService; // <--- ADD THIS IMPORT (adjust package if different)

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.mockito.Mockito; 

@TestConfiguration
public class TestConfig {

    @Bean
    public ComputadorService computadorService() {
        return Mockito.mock(ComputadorService.class);
    }
}