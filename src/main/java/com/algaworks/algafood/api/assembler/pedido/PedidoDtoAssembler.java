package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.model.dtooutput.PedidoDtoOutput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDtoOutput toDtoOutput(Pedido pedido) {
        return modelMapper.map(pedido, PedidoDtoOutput.class);
    }

    public List<PedidoDtoOutput> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toDtoOutput(pedido))
                .collect(Collectors.toList());
    }

}
