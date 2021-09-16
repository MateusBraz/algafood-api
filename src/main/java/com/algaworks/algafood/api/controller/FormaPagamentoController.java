package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.formapagamento.FormaPagamentoDtoAssembler;
import com.algaworks.algafood.api.assembler.formapagamento.FormaPagamentoDtoDisassembler;
import com.algaworks.algafood.api.model.dtoinput.FormaPagamentoDtoInput;
import com.algaworks.algafood.api.model.dtooutput.FormaPagamentoDtoOutput;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoDtoAssembler formaPagamentoDtoAssembler;

    @Autowired
    private FormaPagamentoDtoDisassembler formaPagamentoDtoDisassembler;

    @GetMapping
    public List<FormaPagamentoDtoOutput> listar() {
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
        return formaPagamentoDtoAssembler.toCollectionModel(formasPagamento);
    }

    @GetMapping("/{id}")
    public FormaPagamentoDtoOutput buscar(@PathVariable Long id) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(id);
        return formaPagamentoDtoAssembler.toDtoOutput(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDtoOutput adicionar(@RequestBody @Valid FormaPagamentoDtoInput formaPagamentoDtoInput) {
        FormaPagamento formaPagamento = formaPagamentoDtoDisassembler.toDomainModel(formaPagamentoDtoInput);
        return formaPagamentoDtoAssembler.toDtoOutput(formaPagamentoService.salvar(formaPagamento));
    }

    @PutMapping("/{id}")
    public FormaPagamentoDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoDtoInput formaPagamentoDtoInput) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(id);
        formaPagamentoDtoDisassembler.copyToDomainModel(formaPagamentoDtoInput, formaPagamento);
        return formaPagamentoDtoAssembler.toDtoOutput(formaPagamentoService.salvar(formaPagamento));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        formaPagamentoService.excluir(id);
    }

}
