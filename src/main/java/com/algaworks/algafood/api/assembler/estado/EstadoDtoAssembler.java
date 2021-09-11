package com.algaworks.algafood.api.assembler.estado;

import com.algaworks.algafood.api.model.dtooutput.EstadoDtoOutput;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoDtoOutput toDtoOutput(Estado estado) {
        return modelMapper.map(estado, EstadoDtoOutput.class);
    }

    public List<EstadoDtoOutput> toCollectionModel(List<Estado> estados) {
        return estados.stream()
                .map(estado -> toDtoOutput(estado))
                .collect(Collectors.toList());
    }
    
}
