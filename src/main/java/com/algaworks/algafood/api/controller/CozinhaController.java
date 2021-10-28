package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.cozinha.CozinhaDtoAssembler;
import com.algaworks.algafood.api.assembler.cozinha.CozinhaDtoDisassembler;
import com.algaworks.algafood.api.model.dto.input.CozinhaDtoInput;
import com.algaworks.algafood.api.model.dto.output.CozinhaDtoOutput;
import com.algaworks.algafood.api.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    CozinhaService cozinhaService;

    @Autowired
    CozinhaDtoAssembler cozinhaDtoAssembler;

    @Autowired
    CozinhaDtoDisassembler cozinhaDtoDisassembler;

    @GetMapping
    public Page<CozinhaDtoOutput> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
        List<CozinhaDtoOutput> cozinhasDtoOutput = cozinhaDtoAssembler.toCollectionDtoOutput(cozinhasPage.getContent());
        Page<CozinhaDtoOutput> cozinhasDtoOutputPage = new PageImpl<>(cozinhasDtoOutput, pageable, cozinhasPage.getTotalElements());
        return cozinhasDtoOutputPage;
    }

    @GetMapping("/{id}")
    public CozinhaDtoOutput buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhaService.buscarOuFalhar(id);
        return cozinhaDtoAssembler.toDtoOutput(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDtoOutput adicionar(@Valid @RequestBody CozinhaDtoInput cozinhaDtoInput) {
        Cozinha cozinha = cozinhaDtoDisassembler.toDomainModel(cozinhaDtoInput);
        return cozinhaDtoAssembler.toDtoOutput(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public CozinhaDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaDtoInput cozinhaDtoInput) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(id);
        cozinhaDtoDisassembler.copyToDomainModel(cozinhaDtoInput, cozinhaAtual);
//        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaDtoAssembler.toDtoOutput(cozinhaRepository.save(cozinhaAtual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cozinhaService.excluir(id);
    }

}
