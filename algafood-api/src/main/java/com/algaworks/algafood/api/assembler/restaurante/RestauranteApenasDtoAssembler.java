package com.algaworks.algafood.api.assembler.restaurante;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.dto.output.resumo.RestauranteApenasNomeDtoOutput;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteApenasDtoAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteApenasDtoAssembler() {
        super(RestauranteController.class, RestauranteApenasNomeDtoOutput.class);
    }

    @Override
    public RestauranteApenasNomeDtoOutput toModel(Restaurante restaurante) {
        RestauranteApenasNomeDtoOutput restauranteApenasNomeDtoOutput = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteApenasNomeDtoOutput);
        restauranteApenasNomeDtoOutput.add(algaLinks.linkToRestaurantes("restaurantes"));
        return restauranteApenasNomeDtoOutput;
    }

    @Override
    public CollectionModel<RestauranteApenasNomeDtoOutput> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
    }

}
