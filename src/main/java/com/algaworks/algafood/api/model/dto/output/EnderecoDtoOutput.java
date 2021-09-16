package com.algaworks.algafood.api.model.dto.output;

import com.algaworks.algafood.api.model.dto.output.resumo.CidadeResumoDtoOutput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDtoOutput {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoDtoOutput cidade;

}
