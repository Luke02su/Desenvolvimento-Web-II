package com.pwii.fiis.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pwii.fiis.model.Fii;


@Repository //Indica que a Interface é um componente de repositório Spring
public interface FiiRepository extends JpaRepository <Fii, Long> { //JpaRepository fornece métodos padrão para operações CRUD e paginação

}
