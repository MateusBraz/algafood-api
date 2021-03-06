package com.algaworks.algafood.api.model.dto.output.resumo;

import com.algaworks.algafood.api.model.dto.output.CozinhaDtoOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteBasicoDtoOutput extends RepresentationModel<RestauranteBasicoDtoOutput> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    private String nome;

    @ApiModelProperty(example = "12.00")
    private BigDecimal taxaFrete;

    private CozinhaDtoOutput cozinha;

}
