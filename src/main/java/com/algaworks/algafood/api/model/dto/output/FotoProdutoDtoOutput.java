package com.algaworks.algafood.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoDtoOutput {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

}
