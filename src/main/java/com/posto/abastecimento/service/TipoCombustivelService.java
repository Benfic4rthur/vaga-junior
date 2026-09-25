package com.posto.abastecimento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.posto.abastecimento.model.TipoCombustivel;
import com.posto.abastecimento.repository.TipoCombustivelRepository;

@Service
public class TipoCombustivelService {

    private final TipoCombustivelRepository repository;

    public TipoCombustivelService(TipoCombustivelRepository repository) {
        this.repository = repository;
    }

    public List<TipoCombustivel> listar() {
        return repository.findAll();
    }

    public Optional<TipoCombustivel> buscar(Long id) {
        return repository.findById(id);
    }

    public TipoCombustivel salvar(TipoCombustivel tipoCombustivel) {
        return repository.save(tipoCombustivel);
    }

    public Optional<TipoCombustivel> atualizar(Long id, TipoCombustivel dados) {
        return repository.findById(id).map(tipoCombustivel -> {
            tipoCombustivel.setNome(dados.getNome());
            tipoCombustivel.setPrecoLitro(dados.getPrecoLitro());
            return repository.save(tipoCombustivel);
        });
    }

    public boolean excluir(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
