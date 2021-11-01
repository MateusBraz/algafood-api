package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.model.dto.output.resumo.PedidoResumoDtoOutput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoDtoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoDtoAssembler() {
        super(PedidoController.class, PedidoResumoDtoOutput.class);
    }

    @Override
    public PedidoResumoDtoOutput toModel(Pedido pedido) {
        PedidoResumoDtoOutput pedidoResumoDtoOutput = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoResumoDtoOutput);

        pedidoResumoDtoOutput.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));

        pedidoResumoDtoOutput.getRestaurante().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoResumoDtoOutput.getCliente().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());

        return pedidoResumoDtoOutput;
    }

}
