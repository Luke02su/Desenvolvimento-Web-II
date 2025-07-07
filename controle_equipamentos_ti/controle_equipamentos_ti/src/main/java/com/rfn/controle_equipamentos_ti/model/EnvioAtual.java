package com.rfn.controle_equipamentos_ti.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // Entidade JPA
@Table(name = "envio_equipamento") // Tabela do banco de dados MySQL
public class EnvioAtual {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_envio")
    private Long pk_envio;

    @ManyToOne
    @JoinColumn(name = "fk_num_serie", referencedColumnName = "pk_num_serie")
    private Equipamento equipamento; 

    @Column(name = "origem")
    private String origem; 

    @ManyToOne 
    @JoinColumn(name = "fk_loja", referencedColumnName = "pk_loja")
    private Loja lojaDestino;

    @Column(name = "motivo")
    private String motivo; 

    @Column(name = "data_envio")
    private LocalDate data_envio;

    @Column(name = "usuario_envio")
    private String usuario_envio; 

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Long getPk_envio() {
        return pk_envio;
    }

    public void setPk_envio(Long pk_envio) {
        this.pk_envio = pk_envio;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Loja getLojaDestino() {
        return lojaDestino;
    }

    public void setLojaDestino(Loja lojaDestino) {
        this.lojaDestino = lojaDestino;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getData_envio() {
        return data_envio;
    }

    public void setData_envio(LocalDate data_envio) {
        this.data_envio = data_envio;
    }

    public String getUsuario_envio() {
        return usuario_envio;
    }

    public void setUsuario_envio(String usuario_envio) {
        this.usuario_envio = usuario_envio;
    }
}
