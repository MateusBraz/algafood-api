package com.algaworks.algafood.api.assembler.formapagamento;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.model.dto.output.FormaPagamentoDtoOutput;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoDtoAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public FormaPagamentoDtoAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoDtoOutput.class);
    }

    @Override
    public FormaPagamentoDtoOutput toModel(FormaPagamento formaPagamento) {
        FormaPagamentoDtoOutput formaPagamentoDtoOutput = createModelWithId(formaPagamento.getId(), formaPagamento);

        modelMapper.map(formaPagamento, formaPagamentoDtoOutput);

        if (algaSecurity.podeConsultarFormasPagamento()) {
            formaPagamentoDtoOutput.add(algaLinks.linkToFormasPagamento("formasPagamento"));
        }

        return formaPagamentoDtoOutput;
    }

    @Override
    public CollectionModel<FormaPagamentoDtoOutput> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        CollectionModel<FormaPagamentoDtoOutput> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarFormasPagamento()) {
            collectionModel.add(algaLinks.linkToFormasPagamento());
        }

        return collectionModel;
    }

}
