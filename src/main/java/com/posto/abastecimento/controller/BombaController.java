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

import com.posto.abastecimento.model.Bomba;
import com.posto.abastecimento.service.BombaService;

@RestController
@RequestMapping("/bombas")
public class BombaController {

    private final BombaService service;

    public BombaController(BombaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Bomba> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bomba> buscar(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bomba> criar(@RequestBody Bomba bomba) {
        return service.salvar(bomba)
                .map(salva -> ResponseEntity.status(HttpStatus.CREATED).body(salva))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bomba> atualizar(@PathVariable Long id, @RequestBody Bomba dados) {
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
