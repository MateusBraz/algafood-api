package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public RootEntryPointDtoOutput root() {
        var rootEntryPointDtoOutput = new RootEntryPointDtoOutput();
        rootEntryPointDtoOutput.add(algaLinks.linkToCozinhas("cozinhas"));
        rootEntryPointDtoOutput.add(algaLinks.linkToPedidos("pedidos"));
        rootEntryPointDtoOutput.add(algaLinks.linkToRestaurantes("restaurantes"));
        rootEntryPointDtoOutput.add(algaLinks.linkToGrupos("grupos"));
        rootEntryPointDtoOutput.add(algaLinks.linkToUsuarios("usuarios"));
        rootEntryPointDtoOutput.add(algaLinks.linkToPermissoes("permissoes"));
        rootEntryPointDtoOutput.add(algaLinks.linkToFormasPagamento("formas-pagamento"));
        rootEntryPointDtoOutput.add(algaLinks.linkToEstados("estados"));
        rootEntryPointDtoOutput.add(algaLinks.linkToCidades("cidades"));
        return rootEntryPointDtoOutput;
    }

    private static class RootEntryPointDtoOutput extends RepresentationModel<RootEntryPointDtoOutput> {

    }

}
