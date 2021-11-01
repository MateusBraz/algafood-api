package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.controller.*;
import com.algaworks.algafood.api.model.dto.output.PedidoDtoOutput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class PedidoDtoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDtoAssembler() {
        super(PedidoController.class, PedidoDtoOutput.class);
    }

    public PedidoDtoOutput toModel(Pedido pedido) {
        PedidoDtoOutput pedidoDtoOutput = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoDtoOutput);

        pedidoDtoOutput.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));

        pedidoDtoOutput.getRestaurante().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoDtoOutput.getCliente().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());

        pedidoDtoOutput.getFormaPagamento().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FormaPagamentoController.class)
                .buscar(pedido.getFormaPagamento().getId())).withSelfRel());

        pedidoDtoOutput.getEnderecoEntrega().getCidade().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class)
                .buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoDtoOutput.getItens().forEach(item -> {
            item.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class)
                            .buscar(pedidoDtoOutput.getRestaurante().getId(), item.getProdutoId()))
                    .withRel("produto"));
        });

        return pedidoDtoOutput;
    }

}
