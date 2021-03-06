package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.permissao.PermissaoDtoAssembler;
import com.algaworks.algafood.api.model.dto.output.PermissaoDtoOutput;
import com.algaworks.algafood.api.openapi.controller.PermissaoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoDtoAssembler permissaoDtoAssembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<PermissaoDtoOutput> listar() {
        List<Permissao> permissoes = permissaoRepository.findAll();
        return permissaoDtoAssembler.toCollectionModel(permissoes);
    }

}
