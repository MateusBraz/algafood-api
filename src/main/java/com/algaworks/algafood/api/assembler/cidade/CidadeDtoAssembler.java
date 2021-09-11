package com.algaworks.algafood.api.assembler.cidade;

import com.algaworks.algafood.api.model.dtooutput.CidadeDtoOutput;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeDtoOutput toDtoOutput(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDtoOutput.class);
    }

    public List<CidadeDtoOutput> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
                .map(cidade -> toDtoOutput(cidade))
                .collect(Collectors.toList());
    }

}
