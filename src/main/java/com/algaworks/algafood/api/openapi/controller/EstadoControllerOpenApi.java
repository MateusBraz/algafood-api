package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.input.EstadoDtoInput;
import com.algaworks.algafood.api.model.dto.output.EstadoDtoOutput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @ApiOperation("Lista os estados")
    List<EstadoDtoOutput> listar();

    @ApiOperation("Busca um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoDtoOutput buscar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long id);

    @ApiOperation("Cadastra um estado")
    @ApiResponses({@ApiResponse(code = 201, message = "Estado cadastrado")})
    EstadoDtoOutput adicionar(@ApiParam(name = "corpo", value = "Representação de um novo estado", required = true) EstadoDtoInput estadoDtoInput);

    @ApiOperation("Atualiza um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Estado atualizado"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoDtoOutput atualizar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de um estado com os novos dados", required = true) EstadoDtoInput estadoDtoInput);

    @ApiOperation("Exclui um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado excluído"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID de um estado", example = "1", required = true) Long id);

}
