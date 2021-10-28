package com.algaworks.algafood.api.model.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaDtoInput {

    @ApiModelProperty(example = "Brasileira", required = true)
    @NotBlank
    private String nome;

}
