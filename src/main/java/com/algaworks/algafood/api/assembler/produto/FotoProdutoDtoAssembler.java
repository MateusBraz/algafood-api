package com.algaworks.algafood.api.assembler.produto;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.model.dto.output.FotoProdutoDtoOutput;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoDtoAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public FotoProdutoDtoAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoDtoOutput.class);
    }

    @Override
    public FotoProdutoDtoOutput toModel(FotoProduto fotoProduto) {
        FotoProdutoDtoOutput fotoProdutoModel = modelMapper.map(fotoProduto, FotoProdutoDtoOutput.class);

        if (algaSecurity.podeConsultarRestaurantes()) {
            fotoProdutoModel.add(algaLinks.linkToFotoProduto(fotoProduto.getRestauranteId(), fotoProduto.getProduto().getId()));
            fotoProdutoModel.add(algaLinks.linkToProduto(fotoProduto.getRestauranteId(), fotoProduto.getProduto().getId(), "produto"));
        }

        return fotoProdutoModel;
    }

}
