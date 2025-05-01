package com.pwii.fiis.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity //indica o que é uma entidade jpa
@Table(name = "fii") //especifica a tabela no banco d dados correspondente à entidade
public class Fii {
    @Id //chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) //epecifica a estratégia de geraçao de valores para a chave primária
    private Long id;

    @Column(name = "ticket", nullable = false) // configua detalhes das colunas no bd
    private String ticket;

    @Column(name = "gestora")
    private String gestora;

    @Column(name = "segmento", nullable = false) 
    private String segmento;

    @Column(name = "cotacao", nullable = false) 
    private float cotacao;

    @Column(name = "pvp", nullable = false)
    private float pvp;

    @Column(name = "dy", nullable = false)
    private float dy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getGestora() {
        return gestora;
    }

    public void setGestora(String gestora) {
        this.gestora = gestora;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public float getCotacao() {
        return cotacao;
    }

    public void setCotacao(float cotacao) {
        this.cotacao = cotacao;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }
    
}