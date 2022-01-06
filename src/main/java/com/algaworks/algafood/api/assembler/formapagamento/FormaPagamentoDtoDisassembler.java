package com.algaworks.algafood.api.assembler.formapagamento;

import com.algaworks.algafood.api.model.dto.input.FormaPagamentoDtoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainModel(FormaPagamentoDtoInput formaPagamentoDtoInput) {
        return modelMapper.map(formaPagamentoDtoInput, FormaPagamento.class);
    }

    public void copyToDomainModel(FormaPagamentoDtoInput formaPagamentoDtoInput, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoDtoInput, formaPagamento);
    }

}
