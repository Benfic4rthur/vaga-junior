package com.posto.abastecimento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.posto.abastecimento.model.Abastecimento;
import com.posto.abastecimento.model.Bomba;
import com.posto.abastecimento.model.TipoCombustivel;
import com.posto.abastecimento.service.AbastecimentoService;
import com.posto.abastecimento.service.BombaService;
import com.posto.abastecimento.service.TipoCombustivelService;

@SpringBootTest
@Transactional
class AbastecimentoApplicationTests {

    @Autowired
    private TipoCombustivelService tipoCombustivelService;

    @Autowired
    private BombaService bombaService;

    @Autowired
    private AbastecimentoService abastecimentoService;

    @Test
    void contextoCarrega() {
    }

    @Test
    void cadastraTipoCombustivel() {
        TipoCombustivel tipo = criarTipoCombustivel();

        assertNotNull(tipo.getId());
        assertEquals("Gasolina Comum", tipo.getNome());
        assertEquals(new BigDecimal("6.19"), tipo.getPrecoLitro());
    }

    @Test
    void cadastraBombaRelacionadaAoCombustivel() {
        TipoCombustivel tipo = criarTipoCombustivel();
        Bomba bomba = criarBomba(tipo);

        assertNotNull(bomba.getId());
        assertEquals("Bomba 1", bomba.getNome());
        assertEquals(tipo.getId(), bomba.getTipoCombustivel().getId());
    }

    @Test
    void rejeitaBombaSemCombustivelCadastrado() {
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setId(999L);

        Bomba bomba = new Bomba();
        bomba.setNome("Bomba inválida");
        bomba.setTipoCombustivel(tipo);

        assertTrue(bombaService.salvar(bomba).isEmpty());
    }

    @Test
    void cadastraAbastecimentoRelacionadoABomba() {
        TipoCombustivel tipo = criarTipoCombustivel();
        Bomba bomba = criarBomba(tipo);

        Abastecimento abastecimento = new Abastecimento();
        abastecimento.setBomba(bomba);
        abastecimento.setData(LocalDate.of(2026, 9, 26));
        abastecimento.setValorTotal(new BigDecimal("100.00"));
        abastecimento.setLitragem(new BigDecimal("15.898"));

        Abastecimento salvo = abastecimentoService.salvar(abastecimento).orElseThrow();

        assertNotNull(salvo.getId());
        assertEquals(bomba.getId(), salvo.getBomba().getId());
        assertEquals(new BigDecimal("100.00"), salvo.getValorTotal());
        assertEquals(new BigDecimal("15.898"), salvo.getLitragem());
    }

    @Test
    void rejeitaAbastecimentoSemBombaCadastrada() {
        Bomba bomba = new Bomba();
        bomba.setId(999L);

        Abastecimento abastecimento = new Abastecimento();
        abastecimento.setBomba(bomba);
        abastecimento.setData(LocalDate.now());
        abastecimento.setValorTotal(new BigDecimal("50.00"));
        abastecimento.setLitragem(new BigDecimal("8.000"));

        assertTrue(abastecimentoService.salvar(abastecimento).isEmpty());
    }

    private TipoCombustivel criarTipoCombustivel() {
        TipoCombustivel tipo = new TipoCombustivel();
        tipo.setNome("Gasolina Comum");
        tipo.setPrecoLitro(new BigDecimal("6.19"));
        return tipoCombustivelService.salvar(tipo);
    }

    private Bomba criarBomba(TipoCombustivel tipo) {
        Bomba bomba = new Bomba();
        bomba.setNome("Bomba 1");
        bomba.setTipoCombustivel(tipo);
        return bombaService.salvar(bomba).orElseThrow();
    }
}
