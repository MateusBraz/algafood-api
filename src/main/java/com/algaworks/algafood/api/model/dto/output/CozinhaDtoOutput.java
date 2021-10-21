package com.algaworks.algafood.api.model.dto.output;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaDtoOutput {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @JsonView(RestauranteView.Resumo.class)
    private String nome;

}