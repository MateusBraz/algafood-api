package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.model.dto.output.resumo.PedidoResumoDtoOutput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoDtoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PedidoResumoDtoAssembler() {
        super(PedidoController.class, PedidoResumoDtoOutput.class);
    }

    @Override
    public PedidoResumoDtoOutput toModel(Pedido pedido) {
        PedidoResumoDtoOutput pedidoResumoDtoOutput = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoResumoDtoOutput);

        pedidoResumoDtoOutput.add(algaLinks.linkToPedidos("pedidos"));

        pedidoResumoDtoOutput.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoResumoDtoOutput.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

        return pedidoResumoDtoOutput;
    }

}
