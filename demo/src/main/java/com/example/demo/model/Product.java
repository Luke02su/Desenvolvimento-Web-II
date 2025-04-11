package com.example.demo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity //indica o que é uma entidade jpa
@Table(name = "products") //especifica a tabela no banco d dados correspondente à entidade
public class Product {
    @Id //chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) //epecifica a estratégia de geraçao de valores para a chave primária
    private Long id;

    @Column(name = "name", nullable = false) // configua detalhes das colunas no bd
    private String name;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}