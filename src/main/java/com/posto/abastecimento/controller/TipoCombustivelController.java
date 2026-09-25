package com.posto.abastecimento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.posto.abastecimento.model.TipoCombustivel;
import com.posto.abastecimento.service.TipoCombustivelService;

@RestController
@RequestMapping("/tipos-combustivel")
public class TipoCombustivelController {

    private final TipoCombustivelService service;

    public TipoCombustivelController(TipoCombustivelService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoCombustivel> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCombustivel> buscar(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoCombustivel> criar(@RequestBody TipoCombustivel tipoCombustivel) {
        TipoCombustivel salvo = service.salvar(tipoCombustivel);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCombustivel> atualizar(@PathVariable Long id, @RequestBody TipoCombustivel dados) {
        return service.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!service.excluir(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
