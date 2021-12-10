package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.output.GrupoDtoOutput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

    @ApiOperation("Lista os grupos associados a um usuário")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    CollectionModel<GrupoDtoOutput> listar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Associação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
                    response = Problem.class)
    })
    ResponseEntity<Void> associar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, @ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Desassociação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
                    response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, @ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

}