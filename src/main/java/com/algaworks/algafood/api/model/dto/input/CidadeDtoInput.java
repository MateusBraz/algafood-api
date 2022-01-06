package com.algaworks.algafood.api.model.dto.input;

import com.algaworks.algafood.api.model.dto.input.refinput.EstadoIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeDtoInput {

    @ApiModelProperty(example = "Belém", required = true)
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;

}
