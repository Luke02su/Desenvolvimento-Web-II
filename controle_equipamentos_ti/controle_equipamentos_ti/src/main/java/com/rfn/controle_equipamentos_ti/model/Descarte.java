package com.rfn.controle_equipamentos_ti.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "log_equipamentos_descartados")
public class Descarte {

    @OneToOne
    @JoinColumn(name = "fk_num_serie", referencedColumnName = "pk_num_serie")
    private Equipamento equipamento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_descarte")
    private Long pk_descarte;

    @Column(name = "placa", nullable = false)
    private String placa;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "ultima_localizacao", nullable = false)
    private Long ultima_localizacao;

    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    public Equipamento getEquipamento() {
        return equipamento;
    }
    
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Long getPk_descarte() {
        return pk_descarte;
    }

    public void setPk_descarte(Long pk_descarte) {
        this.pk_descarte = pk_descarte;
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

    public String getMotivo() {
        return motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setUltima_localizacao(Long ultima_localizacao) {
        this.ultima_localizacao = ultima_localizacao;
    }

    public Long getUltima_localizacaoodelo() {
        return ultima_localizacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getUltima_localizacao() {
        return ultima_localizacao;
    }
}
