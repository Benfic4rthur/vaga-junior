package com.posto.abastecimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posto.abastecimento.model.TipoCombustivel;

public interface TipoCombustivelRepository extends JpaRepository<TipoCombustivel, Long> {
}
