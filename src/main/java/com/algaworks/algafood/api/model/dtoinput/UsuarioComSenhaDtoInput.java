package com.algaworks.algafood.api.model.dtoinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioComSenhaDtoInput extends UsuarioDtoInput {

    @NotBlank
    private String senha;

}
