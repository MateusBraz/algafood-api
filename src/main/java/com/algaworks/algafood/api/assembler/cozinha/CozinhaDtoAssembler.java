package com.algaworks.algafood.api.assembler.cozinha;

import com.algaworks.algafood.api.model.dto.output.CozinhaDtoOutput;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDtoOutput toDtoOutput(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDtoOutput.class);
    }

    public List<CozinhaDtoOutput> toCollectionDtoOutput(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toDtoOutput(cozinha))
                .collect(Collectors.toList());
    }

}
