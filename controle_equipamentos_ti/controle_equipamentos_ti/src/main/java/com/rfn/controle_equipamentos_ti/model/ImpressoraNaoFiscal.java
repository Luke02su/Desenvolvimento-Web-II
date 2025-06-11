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
@Table(name = "impressora")
public class ImpressoraNaoFiscal {

    @OneToOne
    @JoinColumn(name = "fk_num_serie", referencedColumnName = "pk_num_serie")
    private Equipamento equipamento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_impressora")
    private Long pk_impressora;   

    @Column(name = "revisao_recente")
    private String revisao_recente;

    @Column(name = "data_revisao")
    private String data_revisao;

    @Column(name = "motivo_revisao")
    private String motivo_revisao;

    public Equipamento getEquipamento() {
        return equipamento;
    }
    
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
    
    public Long getPk_impressora() {
        return pk_impressora;
    }
    
    public void setPk_impressora(Long pk_impressora) {
        this.pk_impressora = pk_impressora;
    }
    
    public String getRevisao_recente() {
        return revisao_recente;
    }
    
    public void setRevisao_recente(String revisao_recente) {
        this.revisao_recente = revisao_recente;
    }

    public String getData_revisao() {
        return data_revisao;
    }
    
    public void setData_revisao(String data_revisao) {
        this.data_revisao = data_revisao;
    }    

    public String getMotivo_revisao() {
        return motivo_revisao;
    }

    public void setMotivo_revisao(String motivo_revisao) {
        this.motivo_revisao = motivo_revisao;
    }

}
