package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PedidoFilter {

    @ApiModelProperty(example = "1")
    private Long clienteId;

    @ApiModelProperty(example = "1", value = "ID do restaurante para filtro da pesquisa")
    private Long restauranteId;

    @ApiModelProperty(example = "2021-10-28T00:00:00Z", value = "Data/hora de criação inicial para filtro da pesquisa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @ApiModelProperty(example = "2021-10-28T10:00:00Z", value = "Data/hora de criação final para filtro da pesquisa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;

}
