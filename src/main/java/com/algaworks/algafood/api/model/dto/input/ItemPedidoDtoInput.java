package com.algaworks.algafood.api.model.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ItemPedidoDtoInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long produtoId;

    @ApiModelProperty(example = "2", required = true)
    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @ApiModelProperty(example = "Pouca pimenta, por favor")
    private String observacao;

}
