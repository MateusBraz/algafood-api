package com.algaworks.algafood.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaDtoOutput extends RepresentationModel<CozinhaDtoOutput> {

    @ApiModelProperty(example = "1")
//    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @ApiModelProperty(example = "Brasileira")
//    @JsonView(RestauranteView.Resumo.class)
    private String nome;

}
