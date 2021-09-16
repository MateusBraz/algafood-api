package com.algaworks.algafood.api.model.dto.input.refinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CozinhaIdInput {

    @NotNull
    private Long id;

}
