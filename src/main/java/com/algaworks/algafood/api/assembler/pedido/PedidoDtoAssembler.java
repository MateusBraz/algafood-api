package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PedidoController;
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

    @Autowired
    private AlgaLinks algaLinks;

    public PedidoDtoAssembler() {
        super(PedidoController.class, PedidoDtoOutput.class);
    }

    public PedidoDtoOutput toModel(Pedido pedido) {
        PedidoDtoOutput pedidoDtoOutput = createModelWithId(pedido.getCodigo(), pedido);

        modelMapper.map(pedido, pedidoDtoOutput);

        pedidoDtoOutput.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));

        pedidoDtoOutput.add(algaLinks.linkToPedidos("pedidos"));

        if (pedido.podeSerConfirmado()) {
            pedidoDtoOutput.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
        }

        if (pedido.podeSerEntregue()) {
            pedidoDtoOutput.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
        }

        if (pedido.podeSerCancelado()) {
            pedidoDtoOutput.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
        }

        pedidoDtoOutput.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoDtoOutput.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

        pedidoDtoOutput.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoDtoOutput.getEnderecoEntrega().getCidade().add(algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

        pedidoDtoOutput.getItens().forEach(item -> {
            item.add(algaLinks.linkToProduto(pedidoDtoOutput.getRestaurante().getId(), item.getProdutoId(), "produto"));
        });

        return pedidoDtoOutput;
    }

}
