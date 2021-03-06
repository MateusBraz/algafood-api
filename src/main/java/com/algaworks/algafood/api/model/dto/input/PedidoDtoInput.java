package com.algaworks.algafood.api.model.dto.input;

import com.algaworks.algafood.api.model.dto.input.refinput.FormaPagamentoIdInput;
import com.algaworks.algafood.api.model.dto.input.refinput.RestauranteIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class PedidoDtoInput {

    @Valid
    @NotNull
    private RestauranteIdInput restaurante;

    @Valid
    @NotNull
    private EnderecoDtoInput enderecoEntrega;

    @Valid
    @NotNull
    private FormaPagamentoIdInput formaPagamento;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoDtoInput> itens;

}
