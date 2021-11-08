package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.formapagamento.FormaPagamentoDtoAssembler;
import com.algaworks.algafood.api.assembler.formapagamento.FormaPagamentoDtoDisassembler;
import com.algaworks.algafood.api.model.dto.input.FormaPagamentoDtoInput;
import com.algaworks.algafood.api.model.dto.output.FormaPagamentoDtoOutput;
import com.algaworks.algafood.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoDtoAssembler formaPagamentoDtoAssembler;

    @Autowired
    private FormaPagamentoDtoDisassembler formaPagamentoDtoDisassembler;

    @GetMapping
    public ResponseEntity<CollectionModel<FormaPagamentoDtoOutput>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
        CollectionModel<FormaPagamentoDtoOutput> formasPagamentoDtoOutput = formaPagamentoDtoAssembler.toCollectionModel(formasPagamento);
        return ResponseEntity.ok()
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
//                .cacheControl(CacheControl.noCache())
//                .cacheControl(CacheControl.noStore())
                .body(formasPagamentoDtoOutput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoDtoOutput> buscar(@PathVariable Long id) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(id);
        FormaPagamentoDtoOutput formaPagamentoDtoOutput = formaPagamentoDtoAssembler.toModel(formaPagamento);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formaPagamentoDtoOutput);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDtoOutput adicionar(@RequestBody @Valid FormaPagamentoDtoInput formaPagamentoDtoInput) {
        FormaPagamento formaPagamento = formaPagamentoDtoDisassembler.toDomainModel(formaPagamentoDtoInput);
        return formaPagamentoDtoAssembler.toModel(formaPagamentoService.salvar(formaPagamento));
    }

    @PutMapping("/{id}")
    public FormaPagamentoDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoDtoInput formaPagamentoDtoInput) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(id);
        formaPagamentoDtoDisassembler.copyToDomainModel(formaPagamentoDtoInput, formaPagamento);
        return formaPagamentoDtoAssembler.toModel(formaPagamentoService.salvar(formaPagamento));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        formaPagamentoService.excluir(id);
    }

}
