package com.algaworks.algafood.api.assembler.cozinha;

import com.algaworks.algafood.api.model.dto.input.CozinhaDtoInput;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainModel(CozinhaDtoInput cozinhaDtoInput) {
        return modelMapper.map(cozinhaDtoInput, Cozinha.class);
    }

    public void copyToDomainModel(CozinhaDtoInput cozinhaDtoInput, Cozinha cozinha) {
        modelMapper.map(cozinhaDtoInput, cozinha);
    }

}
