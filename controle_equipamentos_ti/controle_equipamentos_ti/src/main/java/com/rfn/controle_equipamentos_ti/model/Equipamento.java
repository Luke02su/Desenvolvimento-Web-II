package com.rfn.controle_equipamentos_ti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Entidade JPA
@Table(name = "equipamento") // Tabela do banco de dados MySQL
public class Equipamento {

    @Id
    @Column(name = "pk_num_serie")
    private String pk_num_serie;

    @Column(name = "placa", nullable = false)
    private String placa;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "localizacao_atual", nullable = false)
    private int localizacao_atual;

    @Column(name = "enviado")
    private String enviado = "Não";

    public String getPk_num_serie() {
        return pk_num_serie;
    }
    
    public void setPk_num_serie(String pk_num_serie) {
        this.pk_num_serie = pk_num_serie;
    }
    
    public String getPlaca() {
        return placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public int getLocalizacao_atual() {
        return localizacao_atual;
    }
    
    public void setLocalizacao_atual(int localizacao_atual) {
        this.localizacao_atual = localizacao_atual;
    }
    
    public String getEnviado() {
        return enviado;
    }
    
    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }
}