package com.algaworks.algafood.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoDtoOutput extends RepresentationModel<ProdutoDtoOutput> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Espetinho de Cupim")
    private String nome;

    @ApiModelProperty(example = "Acompanha farinha, mandioca e vinagrete")
    private String descricao;

    @ApiModelProperty(example = "12.50")
    private BigDecimal preco;

    @ApiModelProperty(example = "true")
    private Boolean ativo;

}