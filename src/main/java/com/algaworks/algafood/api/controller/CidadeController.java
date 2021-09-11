package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.cidade.CidadeDtoAssembler;
import com.algaworks.algafood.api.assembler.cidade.CidadeDtoDisassebler;
import com.algaworks.algafood.api.model.dtoinput.CidadeDtoInput;
import com.algaworks.algafood.api.model.dtooutput.CidadeDtoOutput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    CidadeService cidadeService;

    @Autowired
    CidadeDtoAssembler cidadeDtoAssembler;

    @Autowired
    CidadeDtoDisassebler cidadeDtoDisassebler;

    @GetMapping
    public List<CidadeDtoOutput> listar() {
        List<Cidade> cidades = cidadeRepository.findAll();
        return cidadeDtoAssembler.toCollectionModel(cidades);
    }

    @GetMapping("/{id}")
    public CidadeDtoOutput buscar(@PathVariable Long id) {
        Cidade cidade = cidadeService.buscarOuFalhar(id);
        return cidadeDtoAssembler.toDtoOutput(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDtoOutput adicionar(@RequestBody @Valid CidadeDtoInput cidadeDtoInput) {
        try {
            Cidade cidade = cidadeDtoDisassebler.toDomainModel(cidadeDtoInput);
            return cidadeDtoAssembler.toDtoOutput(cidadeService.salvar(cidade));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public CidadeDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid CidadeDtoInput cidadeDtoInput) {
        try {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(id);
            cidadeDtoDisassebler.copyToDomainModel(cidadeDtoInput, cidadeAtual);
//            BeanUtils.copyProperties(cidadeDtoInput, cidadeAtual, "id");
            return cidadeDtoAssembler.toDtoOutput(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cidadeService.excluir(id);
    }
}
