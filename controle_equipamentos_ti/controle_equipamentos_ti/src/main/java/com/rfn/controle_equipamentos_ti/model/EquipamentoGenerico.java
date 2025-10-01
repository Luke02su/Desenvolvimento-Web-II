package com.rfn.controle_equipamentos_ti.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipamento_generico")
public class EquipamentoGenerico {

    @OneToOne
    @JoinColumn(name = "fk_num_serie", referencedColumnName = "pk_num_serie")
    @MapsId
    private Equipamento equipamento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk_computador;
}
