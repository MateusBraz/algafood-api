package com.algaworks.algafood.api.model.dto.input.refinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class FormaPagamentoIdInput {

    @NotNull
    private Long id;

}