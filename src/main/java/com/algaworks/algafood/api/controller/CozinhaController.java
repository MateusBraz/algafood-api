package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.cozinha.CozinhaDtoAssembler;
import com.algaworks.algafood.api.assembler.cozinha.CozinhaDtoDisassebler;
import com.algaworks.algafood.api.model.dtoinput.CozinhaDtoInput;
import com.algaworks.algafood.api.model.dtooutput.CozinhaDtoOutput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    CozinhaService cozinhaService;

    @Autowired
    CozinhaDtoAssembler cozinhaDtoAssembler;

    @Autowired
    CozinhaDtoDisassebler cozinhaDtoDisassebler;

    @GetMapping
    public List<CozinhaDtoOutput> listar() {
        List<Cozinha> cozinhas = cozinhaRepository.findAll();
        return cozinhaDtoAssembler.toCollectionModel(cozinhas);
    }

    @GetMapping("/{id}")
    public CozinhaDtoOutput buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhaService.buscarOuFalhar(id);
        return cozinhaDtoAssembler.toDtoOutput(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDtoOutput adicionar(@Valid @RequestBody CozinhaDtoInput cozinhaDtoInput) {
        Cozinha cozinha = cozinhaDtoDisassebler.toDomainModel(cozinhaDtoInput);
        return cozinhaDtoAssembler.toDtoOutput(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public CozinhaDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaDtoInput cozinhaDtoInput) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(id);
        cozinhaDtoDisassebler.copyToDomainModel(cozinhaDtoInput, cozinhaAtual);
//        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaDtoAssembler.toDtoOutput(cozinhaRepository.save(cozinhaAtual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cozinhaService.excluir(id);
    }
}
