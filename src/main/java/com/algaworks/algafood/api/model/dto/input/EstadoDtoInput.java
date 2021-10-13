package com.algaworks.algafood.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class EstadoDtoInput {

    @NotBlank
    private String nome;

}
