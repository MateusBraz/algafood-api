package com.algaworks.algafood.api.model.dto.input;

import com.algaworks.algafood.api.model.dto.input.refinput.CidadeIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoDtoInput {

    @ApiModelProperty(example = "38400-000", required = true)
    @NotBlank
    private String cep;

    @ApiModelProperty(example = "Rua Floriano Peixoto", required = true)
    @NotBlank
    private String logradouro;

    @ApiModelProperty(example = "1500", required = true)
    @NotBlank
    private String numero;

    @ApiModelProperty(example = "Apto 901")
    private String complemento;

    @ApiModelProperty(example = "Centro", required = true)
    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;

}
