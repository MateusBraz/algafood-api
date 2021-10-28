package com.algaworks.algafood.api.model.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioComSenhaDtoInput extends UsuarioDtoInput {

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String senha;

}
