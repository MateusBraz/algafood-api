package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    @GetMapping
    public RootEntryPointDtoOutput root() {
        var rootEntryPointDtoOutput = new RootEntryPointDtoOutput();
        if (algaSecurity.podeConsultarCozinhas()) {
            rootEntryPointDtoOutput.add(algaLinks.linkToCozinhas("cozinhas"));
        }

        if (algaSecurity.podePesquisarPedidos()) {
            rootEntryPointDtoOutput.add(algaLinks.linkToPedidos("pedidos"));
        }

        if (algaSecurity.podeConsultarRestaurantes()) {
            rootEntryPointDtoOutput.add(algaLinks.linkToRestaurantes("restaurantes"));
        }

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            rootEntryPointDtoOutput.add(algaLinks.linkToGrupos("grupos"));
            rootEntryPointDtoOutput.add(algaLinks.linkToUsuarios("usuarios"));
            rootEntryPointDtoOutput.add(algaLinks.linkToPermissoes("permissoes"));
        }

        if (algaSecurity.podeConsultarFormasPagamento()) {
            rootEntryPointDtoOutput.add(algaLinks.linkToFormasPagamento("formas-pagamento"));
        }

        if (algaSecurity.podeConsultarEstados()) {
            rootEntryPointDtoOutput.add(algaLinks.linkToEstados("estados"));
        }

        if (algaSecurity.podeConsultarEstatisticas()) {
            rootEntryPointDtoOutput.add(algaLinks.linkToCidades("cidades"));
        }

        return rootEntryPointDtoOutput;
    }

    private static class RootEntryPointDtoOutput extends RepresentationModel<RootEntryPointDtoOutput> {

    }

}
