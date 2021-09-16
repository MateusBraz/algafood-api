package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.model.dtooutput.resumo.PedidoResumoDtoOutput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoDtoOutput toDtoOutput(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoDtoOutput.class);
    }

    public List<PedidoResumoDtoOutput> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toDtoOutput(pedido))
                .collect(Collectors.toList());
    }

}
