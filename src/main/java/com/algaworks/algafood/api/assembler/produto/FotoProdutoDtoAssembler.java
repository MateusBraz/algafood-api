package com.algaworks.algafood.api.assembler.produto;

import com.algaworks.algafood.api.model.dto.output.FotoProdutoDtoOutput;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoDtoOutput toDtoOutput(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoDtoOutput.class);
    }

}
