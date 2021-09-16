package com.algaworks.algafood.api.model.dtooutput;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteDtoOutput {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDtoOutput cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDtoOutput endereco;

}
