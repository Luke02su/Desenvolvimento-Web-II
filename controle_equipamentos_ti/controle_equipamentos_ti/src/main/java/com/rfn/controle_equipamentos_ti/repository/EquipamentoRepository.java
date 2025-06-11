package com.rfn.controle_equipamentos_ti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rfn.controle_equipamentos_ti.model.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, String> {}
