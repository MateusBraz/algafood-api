package com.algaworks.algafood.api.model.dtoinput;

import com.algaworks.algafood.api.model.dtoinput.refinput.EstadoIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeDtoInput {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;
    
}
