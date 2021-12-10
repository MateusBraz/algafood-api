package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.usuario.UsuarioDtoAssembler;
import com.algaworks.algafood.api.model.dto.output.UsuarioDtoOutput;
import com.algaworks.algafood.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioDtoAssembler usuarioDtoAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<UsuarioDtoOutput> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        CollectionModel<UsuarioDtoOutput> usuariosDtoOutput = usuarioDtoAssembler.toCollectionModel(restaurante.getResponsaveis())
                .removeLinks()
                .add(algaLinks.linkToResponsaveisRestaurante(restauranteId))
                .add(algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

        usuariosDtoOutput.getContent().stream().forEach(usuarioModel -> {
            usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(
                    restauranteId, usuarioModel.getId(), "desassociar"));
        });

        return usuariosDtoOutput;
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

}
