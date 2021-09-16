package com.algaworks.algafood.api.assembler.grupo;

import com.algaworks.algafood.api.model.dtooutput.GrupoDtoOutput;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoDtoOutput toDtoOutput(Grupo grupo) {
        return modelMapper.map(grupo, GrupoDtoOutput.class);
    }

    public List<GrupoDtoOutput> toCollectionModel(Collection<Grupo> grupos) {
        return grupos.stream()
                .map(grupo -> toDtoOutput(grupo))
                .collect(Collectors.toList());
    }

}
