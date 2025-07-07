package com.rfn.controle_equipamentos_ti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rfn.controle_equipamentos_ti.model.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {}
