package com.algaworks.algafood.api.model.dtoinput.refinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CozinhaIdInput {

    @NotNull
    private Long id;

}
