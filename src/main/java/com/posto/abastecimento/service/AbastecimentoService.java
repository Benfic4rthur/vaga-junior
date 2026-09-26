package com.posto.abastecimento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.posto.abastecimento.model.Abastecimento;
import com.posto.abastecimento.model.Bomba;
import com.posto.abastecimento.repository.AbastecimentoRepository;
import com.posto.abastecimento.repository.BombaRepository;

@Service
public class AbastecimentoService {

    private final AbastecimentoRepository repository;
    private final BombaRepository bombaRepository;

    public AbastecimentoService(AbastecimentoRepository repository, BombaRepository bombaRepository) {
        this.repository = repository;
        this.bombaRepository = bombaRepository;
    }

    public List<Abastecimento> listar() {
        return repository.findAll();
    }

    public Optional<Abastecimento> buscar(Long id) {
        return repository.findById(id);
    }

    public Optional<Abastecimento> salvar(Abastecimento abastecimento) {
        if (abastecimento.getBomba() == null || abastecimento.getBomba().getId() == null) {
            return Optional.empty();
        }

        Optional<Bomba> bomba = bombaRepository.findById(abastecimento.getBomba().getId());

        if (bomba.isEmpty()) {
            return Optional.empty();
        }

        abastecimento.setBomba(bomba.get());
        return Optional.of(repository.save(abastecimento));
    }

    public Optional<Abastecimento> atualizar(Long id, Abastecimento dados) {
        if (dados.getBomba() == null || dados.getBomba().getId() == null) {
            return Optional.empty();
        }

        Optional<Abastecimento> abastecimento = repository.findById(id);
        Optional<Bomba> bomba = bombaRepository.findById(dados.getBomba().getId());

        if (abastecimento.isEmpty() || bomba.isEmpty()) {
            return Optional.empty();
        }

        Abastecimento atual = abastecimento.get();
        atual.setBomba(bomba.get());
        atual.setData(dados.getData());
        atual.setValorTotal(dados.getValorTotal());
        atual.setLitragem(dados.getLitragem());

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
