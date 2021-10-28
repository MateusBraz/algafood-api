package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.grupo.GrupoDtoAssembler;
import com.algaworks.algafood.api.assembler.grupo.GrupoDtoDisassembler;
import com.algaworks.algafood.api.model.dto.input.GrupoDtoInput;
import com.algaworks.algafood.api.model.dto.output.GrupoDtoOutput;
import com.algaworks.algafood.api.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoDtoAssembler grupoDtoAssembler;

    @Autowired
    private GrupoDtoDisassembler grupoDtoDisassembler;

    @GetMapping
    public List<GrupoDtoOutput> listar() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupoDtoAssembler.toCollectionDtoOutput(grupos);
    }

    @GetMapping("/{id}")
    public GrupoDtoOutput buscar(@PathVariable Long id) {
        Grupo grupo = grupoService.buscarOuFalhar(id);
        return grupoDtoAssembler.toDtoOutput(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDtoOutput adicionar(@RequestBody @Valid GrupoDtoInput grupoDtoInput) {
        Grupo grupo = grupoDtoDisassembler.toDomainModel(grupoDtoInput);
        return grupoDtoAssembler.toDtoOutput(grupoService.salvar(grupo));
    }

    @PutMapping("/{id}")
    public GrupoDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid GrupoDtoInput grupoDtoInput) {
        Grupo grupo = grupoService.buscarOuFalhar(id);
        grupoDtoDisassembler.copyToDomainModel(grupoDtoInput, grupo);
        return grupoDtoAssembler.toDtoOutput(grupoService.salvar(grupo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        grupoService.excluir(id);
    }

}
