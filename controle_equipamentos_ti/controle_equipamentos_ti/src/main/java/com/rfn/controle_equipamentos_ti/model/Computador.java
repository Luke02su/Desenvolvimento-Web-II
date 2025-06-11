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
@Table(name = "computador")
public class Computador {

    @OneToOne
    @JoinColumn(name = "fk_num_serie", referencedColumnName = "pk_num_serie")
    private Equipamento equipamento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_computador")
    private Long pk_computador;

    @Column(name = "processador")
    private String processador;

    @Column(name = "memoria")
    private String memoria;

    @Column(name = "windows")
    private String windows;

    @Column(name = "armazenamento")
    private String armazenamento;

    @Column(name = "formatacao")
    private String formatacao;

    @Column(name = "manutencao")
    private String manutencao;

    public Equipamento getEquipamento() {
        return equipamento;
    }
    
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
    
    public Long getPk_computador() {
        return pk_computador;
    }
    
    public void setPk_computador(Long pk_computador) {
        this.pk_computador = pk_computador;
    }
    
    public String getProcessador() {
        return processador;
    }
    
    public void setProcessador(String processador) {
        this.processador = processador;
    }
    
    public String getMemoria() {
        return memoria;
    }
    
    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }
    
    public String getWindows() {
        return windows;
    }
    
    public void setWindows(String windows) {
        this.windows = windows;
    }
    
    public String getArmazenamento() {
        return armazenamento;
    }
    
    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }
    
    public String getFormatacao() {
        return formatacao;
    }
    
    public void setFormatacao(String formatacao) {
        this.formatacao = formatacao;
    }
    
    public String getManutencao() {
        return manutencao;
    }
    
    public void setManutencao(String manutencao) {
        this.manutencao = manutencao;
    }    
}
