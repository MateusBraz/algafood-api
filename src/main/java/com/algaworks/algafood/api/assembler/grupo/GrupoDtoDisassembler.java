package com.algaworks.algafood.api.assembler.grupo;

import com.algaworks.algafood.api.model.dto.input.GrupoDtoInput;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Grupo toDomainModel(GrupoDtoInput grupoDtoInput) {
        return modelMapper.map(grupoDtoInput, Grupo.class);
    }

    public void copyToDomainModel(GrupoDtoInput grupoDtoInput, Grupo grupo) {
        modelMapper.map(grupoDtoInput, grupo);
    }

}
