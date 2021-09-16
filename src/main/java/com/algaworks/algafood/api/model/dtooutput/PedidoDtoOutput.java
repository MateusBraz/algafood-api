package com.algaworks.algafood.api.model.dtooutput;

import com.algaworks.algafood.api.model.dtooutput.resumo.RestauranteResumoDtoOutput;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
public class PedidoDtoOutput {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private RestauranteResumoDtoOutput restaurante;
    private UsuarioDtoOutput cliente;
    private FormaPagamentoDtoOutput formaPagamento;
    private EnderecoDtoOutput enderecoEntrega;
    private List<ItemPedidoDtoOutput> itens;

}
