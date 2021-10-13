package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.pedido.PedidoDtoAssembler;
import com.algaworks.algafood.api.assembler.pedido.PedidoDtoDisassembler;
import com.algaworks.algafood.api.assembler.pedido.PedidoResumoDtoAssembler;
import com.algaworks.algafood.api.model.dto.input.PedidoDtoInput;
import com.algaworks.algafood.api.model.dto.output.PedidoDtoOutput;
import com.algaworks.algafood.api.model.dto.output.resumo.PedidoResumoDtoOutput;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.PedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;
import com.google.common.collect.ImmutableBiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoDtoAssembler pedidoDtoAssembler;

    @Autowired
    private PedidoResumoDtoAssembler pedidoResumoDtoAssembler;

    @Autowired
    private PedidoDtoDisassembler pedidoDtoDisassembler;

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        List<PedidoResumoDtoOutput> pedidosDtoOutput = pedidoResumoDtoAssembler.toCollectionDtoOutput(pedidos);
//
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosDtoOutput);
//
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//        if (StringUtils.isNotBlank(campos)) {
//            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//
//        pedidosWrapper.setFilters(filterProvider);
//
//        return pedidosWrapper;
//    }

    @GetMapping
    public Page<PedidoResumoDtoOutput> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);
        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
        List<PedidoResumoDtoOutput> pedidosResumoDtoOutputs = pedidoResumoDtoAssembler.toCollectionDtoOutput(pedidosPage.getContent());
        Page<PedidoResumoDtoOutput> pedidosResumoDtoOutputPage = new PageImpl<>(pedidosResumoDtoOutputs, pageable, pedidosPage.getTotalElements());
        return pedidosResumoDtoOutputPage;
    }

    @GetMapping("/{codigoPedido}")
    public PedidoDtoOutput buscar(@PathVariable String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        return pedidoDtoAssembler.toDtoOutput(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDtoOutput adicionar(@Valid @RequestBody PedidoDtoInput pedidoDtoInput) {
        try {
            Pedido novoPedido = pedidoDtoDisassembler.toDomainModel(pedidoDtoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = pedidoService.emitir(novoPedido);

            return pedidoDtoAssembler.toDtoOutput(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable pageable) {
        var mapeamento = ImmutableBiMap.of(
                "codigo", "codigo",
                "subtotal", "subtotal",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(pageable, mapeamento);
    }

}
