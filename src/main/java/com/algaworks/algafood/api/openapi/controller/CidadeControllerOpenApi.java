package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.input.CidadeDtoInput;
import com.algaworks.algafood.api.model.dto.output.CidadeDtoOutput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    List<CidadeDtoOutput> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class), @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)})
    CidadeDtoOutput buscar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long id);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses({@ApiResponse(code = 201, message = "Cidade cadastrada")})
    CidadeDtoOutput adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true) CidadeDtoInput cidadeDtoInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({@ApiResponse(code = 200, message = "Cidade atualizada"), @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)})
    CidadeDtoOutput atualizar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de uma cidade com os dados atualizados") CidadeDtoInput cidadeDtoInput);

    @ApiOperation("Exclui uma cidade por ID")
    @ApiResponses({@ApiResponse(code = 204, message = "Cidade excluída"), @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)})
    void remover(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long id);

}