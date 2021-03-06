package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.assembler.cidade.CidadeDtoAssembler;
import com.algaworks.algafood.api.assembler.cidade.CidadeDtoDisassembler;
import com.algaworks.algafood.api.model.dto.input.CidadeDtoInput;
import com.algaworks.algafood.api.model.dto.output.CidadeDtoOutput;
import com.algaworks.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    CidadeService cidadeService;

    @Autowired
    CidadeDtoAssembler cidadeDtoAssembler;

    @Autowired
    CidadeDtoDisassembler cidadeDtoDisassembler;

    @CheckSecurity.Cidades.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<CidadeDtoOutput> listar() {
        List<Cidade> cidades = cidadeRepository.findAll();
        return cidadeDtoAssembler.toCollectionModel(cidades);
    }

    @CheckSecurity.Cidades.PodeConsultar
    @Override
    @GetMapping("/{id}")
    public CidadeDtoOutput buscar(@PathVariable Long id) {
        Cidade cidade = cidadeService.buscarOuFalhar(id);

//        cidadeDtoOutput.add(new Link("http://localhost:8080/cidades/1", IanaLinkRelations.SELF));
//        cidadeDtoOutput.add(new Link("http://localhost:8080/cidades/1", IanaLinkRelations.COLLECTION));
//        cidadeDtoOutput.add(new Link("http://localhost:8080/cidades/1"));
//        cidadeDtoOutput.add(new Link("http://localhost:8080/cidades", "cidades"));
//        cidadeDtoOutput.getEstado().add(new Link("http://localhost:8080/estados/1"));

//        cidadeDtoOutput.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
//                .slash(cidadeDtoOutput.getId()).withSelfRel());

//        cidadeDtoOutput.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
//                .withRel("cidades"));

//        cidadeDtoOutput.getEstado().add(WebMvcLinkBuilder.linkTo(EstadoController.class)
//                .slash(cidadeDtoOutput.getEstado().getId()).withSelfRel());

        return cidadeDtoAssembler.toModel(cidade);
    }

    @CheckSecurity.Cidades.PodeEditar
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDtoOutput adicionar(@RequestBody @Valid CidadeDtoInput cidadeDtoInput) {
        try {
            Cidade cidade = cidadeDtoDisassembler.toDomainModel(cidadeDtoInput);
            CidadeDtoOutput cidadeDtoOutput = cidadeDtoAssembler.toModel(cidadeService.salvar(cidade));
            ResourceUriHelper.addUriInResponseHeader(cidadeDtoOutput.getId());
            return cidadeDtoOutput;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Cidades.PodeEditar
    @Override
    @PutMapping("/{id}")
    public CidadeDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid CidadeDtoInput cidadeDtoInput) {
        try {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(id);
            cidadeDtoDisassembler.copyToDomainModel(cidadeDtoInput, cidadeAtual);
//            BeanUtils.copyProperties(cidadeDtoInput, cidadeAtual, "id");
            return cidadeDtoAssembler.toModel(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Cidades.PodeEditar
    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cidadeService.excluir(id);
    }

}
