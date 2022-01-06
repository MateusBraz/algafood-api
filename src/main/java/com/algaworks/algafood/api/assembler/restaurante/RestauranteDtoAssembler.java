package com.algaworks.algafood.api.assembler.restaurante;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.dto.output.RestauranteDtoOutput;
import com.algaworks.algafood.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public RestauranteDtoAssembler() {
        super(RestauranteController.class, RestauranteDtoOutput.class);
    }

    @Override
    public RestauranteDtoOutput toModel(Restaurante restaurante) {
        RestauranteDtoOutput restauranteDtoOutput = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteDtoOutput);

        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteDtoOutput.add(algaLinks.linkToRestaurantes("restaurantes"));
        }

        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
            if (restaurante.ativacaoPermitida()) {
                restauranteDtoOutput.add(algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
            }

            if (restaurante.inativacaoPermitida()) {
                restauranteDtoOutput.add(algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
            }
        }

        if (algaSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
            if (restaurante.aberturaPermitida()) {
                restauranteDtoOutput.add(algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
            }

            if (restaurante.fechamentoPermitido()) {
                restauranteDtoOutput.add(algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
            }
        }

        if (algaSecurity.podeConsultarCidades()) {
            if (restauranteDtoOutput.getEndereco() != null && restauranteDtoOutput.getEndereco().getCidade() != null) {
                restauranteDtoOutput.getEndereco().getCidade().add(algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
            }
        }

        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteDtoOutput.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));
        }

        if (algaSecurity.podeConsultarCozinhas()) {
            restauranteDtoOutput.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
        }

        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteDtoOutput.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(), "formas-pagamento"));
        }

        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
            restauranteDtoOutput.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(), "responsaveis"));
        }
        return restauranteDtoOutput;

    }

    @Override
    public CollectionModel<RestauranteDtoOutput> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteDtoOutput> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(algaLinks.linkToRestaurantes());
        }

        return collectionModel;
    }

}
