package com.rfn.controle_equipamentos_ti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rfn.controle_equipamentos_ti.model.Descarte;

@Repository
public interface DescarteRepository extends JpaRepository<Descarte, Long>  {}
