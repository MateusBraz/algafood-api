package com.algaworks.algafood.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagamentoDtoInput {

    @NotBlank
    private String descricao;

}
