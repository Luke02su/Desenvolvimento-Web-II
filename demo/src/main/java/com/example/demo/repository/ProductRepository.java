package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Product;

@Repository //Indica que a Interdace é um componente de repositório Spring
public interface ProductRepository extends JpaRepository <Product, Long> { //JpaRepository fornece métodos padrão para operações CRUD e paginação

}
