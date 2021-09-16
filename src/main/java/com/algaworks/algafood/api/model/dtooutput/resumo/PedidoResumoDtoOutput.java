package com.algaworks.algafood.api.model.dtooutput.resumo;

import com.algaworks.algafood.api.model.dtooutput.UsuarioDtoOutput;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class PedidoResumoDtoOutput {
    
    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private RestauranteResumoDtoOutput restaurante;
    private UsuarioDtoOutput cliente;

}
