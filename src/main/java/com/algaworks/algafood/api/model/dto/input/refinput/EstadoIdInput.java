package com.algaworks.algafood.api.model.dto.input.refinput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EstadoIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;

}
