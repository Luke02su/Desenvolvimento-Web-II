package com.rfn.controle_equipamentos_ti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipamento_generico")
public class Generico {

    @OneToOne
    @JoinColumn(name = "fk_num_serie", referencedColumnName = "pk_num_serie")
    private Equipamento equipamento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_equipamento_generico")
    private Long pk_equipamento_generico;

    @Column(name = "descricao")
    private String descricao;

    public Equipamento getEquipamento() {
        return equipamento;
    }
    
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
    
    public Long getPk_equipamento_generico() {
        return pk_equipamento_generico;
    }
    
    public void setPk_equipamento_generico(Long pk_equipamento_generico) {
        this.pk_equipamento_generico = pk_equipamento_generico;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
