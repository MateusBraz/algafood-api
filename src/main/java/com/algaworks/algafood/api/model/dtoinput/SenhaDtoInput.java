package com.algaworks.algafood.api.model.dtoinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SenhaDtoInput {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;

}
