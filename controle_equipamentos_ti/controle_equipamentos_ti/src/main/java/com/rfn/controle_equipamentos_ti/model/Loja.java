package com.rfn.controle_equipamentos_ti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loja") 
public class Loja {

    @Id
    @Column(name = "pk_loja")
    private Long pk_loja; 

    @Column(name = "cnpj", unique = true, nullable = false, length = 18)
    private String cnpj;

    @Column(name = "gerente", nullable = false, length = 30)
    private String gerente;

    @Column(name = "cidade", length = 40)
    private String cidade; 

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone; 

    public Loja() {
    }


    public Long getPk_loja() {
        return pk_loja;
    }

    public void setPk_loja(Long pk_loja) {
        this.pk_loja = pk_loja;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}