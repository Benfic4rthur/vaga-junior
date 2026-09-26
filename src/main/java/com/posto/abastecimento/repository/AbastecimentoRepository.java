package com.posto.abastecimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posto.abastecimento.model.Abastecimento;

public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
}
