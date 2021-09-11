package com.algaworks.algafood.api.model.dtooutput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDtoOutput {

    private Long id;
    private String nome;
    private EstadoDtoOutput estado;

}
