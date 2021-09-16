package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.model.dtoinput.PedidoDtoInput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pedido toDomainModel(PedidoDtoInput pedidoDtoInput) {
        return modelMapper.map(pedidoDtoInput, Pedido.class);
    }

    public void copyToDomainModel(PedidoDtoInput pedidoDtoInput, Pedido pedido) {
        modelMapper.map(pedidoDtoInput, pedido);
    }

}
