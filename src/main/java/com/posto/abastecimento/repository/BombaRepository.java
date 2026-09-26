package com.posto.abastecimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posto.abastecimento.model.Bomba;

public interface BombaRepository extends JpaRepository<Bomba, Long> {
}
