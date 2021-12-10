package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "https://algafood.com.br/dados-invalidos", position = 5)
    private String type;

    @ApiModelProperty(example = "Dados inválidos", position = 10)
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 15)
    private String detail;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 20)
    private String userMessage;

    @ApiModelProperty(example = "2021-10-27T12:47:12.748807Z", position = 25)
    private OffsetDateTime timestamp;

    @ApiModelProperty(value = "Lista de Objetos ou campos que geraram o erro (opcional)", position = 30)
    private List<Object> objects;

    @ApiModel("ObjetoProblema")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "preco")
        private String name;

        @ApiModelProperty(example = "Preço do produto é obrigatório")
        private String userMessage;

    }
}