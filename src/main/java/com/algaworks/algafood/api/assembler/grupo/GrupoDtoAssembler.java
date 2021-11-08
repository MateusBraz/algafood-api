package com.algaworks.algafood.api.assembler.grupo;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.GrupoController;
import com.algaworks.algafood.api.model.dto.output.GrupoDtoOutput;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GrupoDtoAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public GrupoDtoAssembler() {
        super(GrupoController.class, GrupoDtoOutput.class);
    }

    @Override
    public GrupoDtoOutput toModel(Grupo grupo) {
        GrupoDtoOutput grupoDtoOutput = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoDtoOutput);

        grupoDtoOutput.add(algaLinks.linkToGrupos("grupos"));

        grupoDtoOutput.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));

        return grupoDtoOutput;
    }

    @Override
    public CollectionModel<GrupoDtoOutput> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToGrupos());
    }

}
