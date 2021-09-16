package com.algaworks.algafood.api.assembler.formapagamento;

import com.algaworks.algafood.api.model.dtooutput.FormaPagamentoDtoOutput;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoDtoOutput toDtoOutput(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoDtoOutput.class);
    }

    public List<FormaPagamentoDtoOutput> toCollectionModel(Collection<FormaPagamento> formasPagamento) {
        return formasPagamento.stream()
                .map(formaPagamento -> toDtoOutput(formaPagamento))
                .collect(Collectors.toList());
    }

}
