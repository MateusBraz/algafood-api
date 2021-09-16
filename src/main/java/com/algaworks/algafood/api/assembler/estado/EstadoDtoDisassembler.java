package com.algaworks.algafood.api.assembler.estado;

import com.algaworks.algafood.api.model.dtoinput.EstadoDtoInput;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Estado toDomainModel(EstadoDtoInput estadoDtoInput) {
        return modelMapper.map(estadoDtoInput, Estado.class);
    }

    public void copyToDomainModel(EstadoDtoInput estadoDtoInput, Estado estado) {
        modelMapper.map(estadoDtoInput, estado);
    }

}
