package com.posto.abastecimento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Abastecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bomba_id", nullable = false)
    private Bomba bomba;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal litragem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bomba getBomba() {
        return bomba;
    }

    public void setBomba(Bomba bomba) {
        this.bomba = bomba;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getLitragem() {
        return litragem;
    }

    public void setLitragem(BigDecimal litragem) {
        this.litragem = litragem;
    }
}
