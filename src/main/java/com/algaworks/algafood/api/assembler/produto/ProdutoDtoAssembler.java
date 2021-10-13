package com.algaworks.algafood.api.assembler.produto;

import com.algaworks.algafood.api.model.dto.output.ProdutoDtoOutput;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDtoOutput toDtoOutput(Produto produto) {
        return modelMapper.map(produto, ProdutoDtoOutput.class);
    }

    public List<ProdutoDtoOutput> toCollectionDtoOutput(List<Produto> produtos) {
        return produtos.stream()
                .map(produto -> toDtoOutput(produto))
                .collect(Collectors.toList());
    }

}
