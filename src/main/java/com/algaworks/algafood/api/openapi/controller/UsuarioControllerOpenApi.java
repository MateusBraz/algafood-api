package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.input.SenhaDtoInput;
import com.algaworks.algafood.api.model.dto.input.UsuarioComSenhaDtoInput;
import com.algaworks.algafood.api.model.dto.input.UsuarioDtoInput;
import com.algaworks.algafood.api.model.dto.output.UsuarioDtoOutput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

    @ApiOperation("Lista os usuários")
    CollectionModel<UsuarioDtoOutput> listar();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioDtoOutput buscar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses({@ApiResponse(code = 201, message = "Usuário cadastrado")})
    UsuarioDtoOutput adicionar( @ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true) UsuarioComSenhaDtoInput usuarioInput);

    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioDtoOutput atualizar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados", required = true) UsuarioDtoInput usuarioInput);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void alterarSenha(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, @ApiParam(name = "corpo", value = "Representação de uma nova senha", required = true) SenhaDtoInput senhaDtoInput);

}