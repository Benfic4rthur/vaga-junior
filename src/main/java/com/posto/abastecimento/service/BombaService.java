package com.posto.abastecimento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.posto.abastecimento.model.Bomba;
import com.posto.abastecimento.model.TipoCombustivel;
import com.posto.abastecimento.repository.BombaRepository;
import com.posto.abastecimento.repository.TipoCombustivelRepository;

@Service
public class BombaService {

    private final BombaRepository repository;
    private final TipoCombustivelRepository tipoCombustivelRepository;

    public BombaService(BombaRepository repository, TipoCombustivelRepository tipoCombustivelRepository) {
        this.repository = repository;
        this.tipoCombustivelRepository = tipoCombustivelRepository;
    }

    public List<Bomba> listar() {
        return repository.findAll();
    }

    public Optional<Bomba> buscar(Long id) {
        return repository.findById(id);
    }

    public Optional<Bomba> salvar(Bomba bomba) {
        if (bomba.getTipoCombustivel() == null || bomba.getTipoCombustivel().getId() == null) {
            return Optional.empty();
        }

        Optional<TipoCombustivel> tipoCombustivel = tipoCombustivelRepository.findById(
                bomba.getTipoCombustivel().getId());

        if (tipoCombustivel.isEmpty()) {
            return Optional.empty();
        }

        bomba.setTipoCombustivel(tipoCombustivel.get());
        return Optional.of(repository.save(bomba));
    }

    public Optional<Bomba> atualizar(Long id, Bomba dados) {
        if (dados.getTipoCombustivel() == null || dados.getTipoCombustivel().getId() == null) {
            return Optional.empty();
        }

        Optional<Bomba> bomba = repository.findById(id);
        Optional<TipoCombustivel> tipoCombustivel = tipoCombustivelRepository.findById(
                dados.getTipoCombustivel().getId());

        if (bomba.isEmpty() || tipoCombustivel.isEmpty()) {
            return Optional.empty();
        }

        Bomba atual = bomba.get();
        atual.setNome(dados.getNome());
        atual.setTipoCombustivel(tipoCombustivel.get());

        return Optional.of(repository.save(atual));
    }

    public boolean excluir(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
