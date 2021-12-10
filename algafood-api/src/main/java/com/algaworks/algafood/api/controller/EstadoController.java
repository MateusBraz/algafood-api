package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.estado.EstadoDtoAssembler;
import com.algaworks.algafood.api.assembler.estado.EstadoDtoDisassembler;
import com.algaworks.algafood.api.model.dto.input.EstadoDtoInput;
import com.algaworks.algafood.api.model.dto.output.EstadoDtoOutput;
import com.algaworks.algafood.api.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    EstadoService estadoService;

    @Autowired
    EstadoDtoAssembler estadoDtoAssembler;

    @Autowired
    EstadoDtoDisassembler estadoDtoDisassembler;

    @GetMapping
    public CollectionModel<EstadoDtoOutput> listar() {
        List<Estado> estados = estadoRepository.findAll();
        return estadoDtoAssembler.toCollectionModel(estados);
    }

    @GetMapping("/{id}")
    public EstadoDtoOutput buscar(@PathVariable Long id) {
        Estado estado = estadoService.buscarOuFalhar(id);
        return estadoDtoAssembler.toModel(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDtoOutput adicionar(@RequestBody @Valid EstadoDtoInput estadoDtoInput) {
        Estado estado = estadoDtoDisassembler.toDomainModel(estadoDtoInput);
        return estadoDtoAssembler.toModel(estadoService.salvar(estado));
    }

    @PutMapping("/{id}")
    public EstadoDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid EstadoDtoInput estadoDtoInput) {
        Estado estadoAtual = estadoService.buscarOuFalhar(id);
        estadoDtoDisassembler.copyToDomainModel(estadoDtoInput, estadoAtual);
//        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoDtoAssembler.toModel(estadoService.salvar(estadoAtual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        estadoService.excluir(id);
    }

}