package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CadastroCozinhaIntegrationTests {

    @Autowired
    CozinhaService cozinhaService;

    @Test
    public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // ação
        novaCozinha = cozinhaService.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        assertThrows(ConstraintViolationException.class, () -> cozinhaService.salvar(novaCozinha));
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
        assertThrows(EntidadeEmUsoException.class, () -> cozinhaService.excluir(1L));
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {
        assertThrows(CozinhaNaoEncontradaException.class, () -> cozinhaService.excluir(100L));
    }

}
