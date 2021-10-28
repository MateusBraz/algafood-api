package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.input.CozinhaDtoInput;
import com.algaworks.algafood.api.model.dto.output.CozinhaDtoOutput;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @ApiOperation("Lista as cozinhas compaginação")
    Page<CozinhaDtoOutput> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponses({@ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class), @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)})
    CozinhaDtoOutput buscar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long id);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses({@ApiResponse(code = 201, message = "Cozinha cadastrada")})
    CozinhaDtoOutput adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true) CozinhaDtoInput cozinhaDtoInput);

    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({@ApiResponse(code = 200, message = "Cozinha atualizada"), @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)})
    CozinhaDtoOutput atualizar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados") CozinhaDtoInput cozinhaDtoInput);

    @ApiOperation("Exclui uma cozinha por ID")
    @ApiResponses({@ApiResponse(code = 204, message = "Cozinha excluída"), @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)})
    void remover(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long id);

}
