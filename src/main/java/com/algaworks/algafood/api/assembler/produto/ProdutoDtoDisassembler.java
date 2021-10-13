package com.algaworks.algafood.api.assembler.produto;

import com.algaworks.algafood.api.model.dto.input.ProdutoDtoInput;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainModel(ProdutoDtoInput produtoDtoInput) {
        return modelMapper.map(produtoDtoInput, Produto.class);
    }

    public void copyToDomainModel(ProdutoDtoInput produtoDtoInput, Produto produto) {
        modelMapper.map(produtoDtoInput, produto);
    }

}
