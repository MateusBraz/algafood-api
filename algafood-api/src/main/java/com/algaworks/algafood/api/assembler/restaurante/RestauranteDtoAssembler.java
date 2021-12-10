package com.algaworks.algafood.api.assembler.restaurante;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.dto.output.RestauranteDtoOutput;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDtoAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteDtoAssembler() {
        super(RestauranteController.class, RestauranteDtoOutput.class);
    }

    @Override
    public RestauranteDtoOutput toModel(Restaurante restaurante) {
        RestauranteDtoOutput restauranteDtoOutput = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteDtoOutput);

        restauranteDtoOutput.add(algaLinks.linkToRestaurantes("restaurantes"));

        if (restaurante.ativacaoPermitida()) {
            restauranteDtoOutput.add(algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()) {
            restauranteDtoOutput.add(algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        }

        if (restaurante.aberturaPermitida()) {
            restauranteDtoOutput.add(algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        }

        if (restaurante.fechamentoPermitido()) {
            restauranteDtoOutput.add(algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        }

        if (restauranteDtoOutput.getEndereco() != null && restauranteDtoOutput.getEndereco().getCidade() != null) {
            restauranteDtoOutput.getEndereco().getCidade().add(algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        }

        restauranteDtoOutput.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));

        restauranteDtoOutput.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

        restauranteDtoOutput.getEndereco().getCidade().add(algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));

        restauranteDtoOutput.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(), "formas-pagamento"));

        restauranteDtoOutput.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(), "responsaveis"));

        return restauranteDtoOutput;

    }

    @Override
    public CollectionModel<RestauranteDtoOutput> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
    }

}
