package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long id) {
        Restaurante restaurante = buscarOuFalhar(id);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long id) {
        Restaurante restaurante = buscarOuFalhar(id);
        restaurante.inativar();
    }

    @Transactional
    public void ativar(List<Long> restauranteIds) {
        restauranteIds.forEach(this::ativar);
    }

    @Transactional
    public void inativar(List<Long> restauranteIds) {
        restauranteIds.forEach(this::inativar);
    }

    @Transactional
    public void associarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        restaurante.adicionarResponsavel(usuario);
    }

    @Transactional
    public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        restaurante.removerResponsavel(usuario);
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.fechar();
    }

    public Restaurante buscarOuFalhar(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }

}
