package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.input.PedidoDtoInput;
import com.algaworks.algafood.api.model.dto.output.PedidoDtoOutput;
import com.algaworks.algafood.api.model.dto.output.resumo.PedidoResumoDtoOutput;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiImplicitParams({@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula", name = "campos", paramType = "query", type = "string")})
    @ApiOperation("Pesquisa os pedidos")
    PagedModel<PedidoResumoDtoOutput> pesquisar(PedidoFilter filtro, Pageable pageable);

    @ApiImplicitParams({@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula", name = "campos", paramType = "query", type = "string")})
    @ApiOperation("Busca um pedido por código")
    @ApiResponses({@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)})
    PedidoDtoOutput buscar(@ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);

    @ApiOperation("Registra um pedido")
    @ApiResponses({@ApiResponse(code = 201, message = "Pedido registrado")})
    PedidoDtoOutput adicionar(@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true) PedidoDtoInput pedidoDtoInput);

}
