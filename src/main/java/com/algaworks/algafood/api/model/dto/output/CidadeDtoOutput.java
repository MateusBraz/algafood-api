package com.algaworks.algafood.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeDtoOutput {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Belém")
    private String nome;
    private EstadoDtoOutput estado;

}
