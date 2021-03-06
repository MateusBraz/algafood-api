package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.model.dto.output.PermissaoDtoOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões")
    CollectionModel<PermissaoDtoOutput> listar();

}
