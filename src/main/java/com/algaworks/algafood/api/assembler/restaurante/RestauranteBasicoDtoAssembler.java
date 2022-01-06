package com.algaworks.algafood.api.assembler.restaurante;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.dto.output.resumo.RestauranteBasicoDtoOutput;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteBasicoDtoAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public RestauranteBasicoDtoAssembler() {
        super(RestauranteController.class, RestauranteBasicoDtoOutput.class);
    }

    @Override
    public RestauranteBasicoDtoOutput toModel(Restaurante restaurante) {
        RestauranteBasicoDtoOutput restauranteBasicoDtoOutput = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteBasicoDtoOutput);

        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteBasicoDtoOutput.add(algaLinks.linkToRestaurantes("restaurantes"));
        }

        if (algaSecurity.podeConsultarCozinhas()) {
            restauranteBasicoDtoOutput.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
        }

        return restauranteBasicoDtoOutput;
    }

    @Override
    public CollectionModel<RestauranteBasicoDtoOutput> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteBasicoDtoOutput> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(algaLinks.linkToRestaurantes());
        }

        return collectionModel;
    }

}
