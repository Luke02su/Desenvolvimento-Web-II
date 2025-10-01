package com.rfn.controle_equipamentos_ti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rfn.controle_equipamentos_ti.model.Computador;

@Repository
public interface ComputadorRepository extends JpaRepository<Computador, Long> {
    @Query(value = "SELECT * FROM computador ORDER BY pk_computador DESC", nativeQuery = true)
    List<Computador> findAllByOrderByPk_computadorDesc();


}