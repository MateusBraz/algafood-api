package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.input.GrupoDtoInput;
import com.algaworks.algafood.api.model.dto.output.GrupoDtoOutput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation("Lista os grupos")
    List<GrupoDtoOutput> listar();

    @ApiOperation("Busca um grupo por ID")
    @ApiResponses({@ApiResponse(code = 400, message = "ID da grupo inválido", response = Problem.class), @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
    GrupoDtoOutput buscar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long id);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses({@ApiResponse(code = 201, message = "Grupo cadastrado")})
    GrupoDtoOutput adicionar(@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true) GrupoDtoInput grupoDtoInput);

    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponses({@ApiResponse(code = 200, message = "Grupo atualizado"), @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
    GrupoDtoOutput atualizar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados") GrupoDtoInput grupoDtoInput);

    @ApiOperation("Exclui um grupo por ID")
    @ApiResponses({@ApiResponse(code = 204, message = "Grupo excluído"), @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
    void remover(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long id);

}
