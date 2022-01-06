package com.algaworks.algafood.api.assembler.permissao;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.model.dto.output.PermissaoDtoOutput;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissaoDtoAssembler implements RepresentationModelAssembler<Permissao, PermissaoDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @Override
    public PermissaoDtoOutput toModel(Permissao permissao) {
        PermissaoDtoOutput permissaoDtoOutput = modelMapper.map(permissao, PermissaoDtoOutput.class);
        return permissaoDtoOutput;
    }

    @Override
    public CollectionModel<PermissaoDtoOutput> toCollectionModel(Iterable<? extends Permissao> entities) {
        CollectionModel<PermissaoDtoOutput> collectionModel = RepresentationModelAssembler.super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            collectionModel.add(algaLinks.linkToPermissoes());
        }

        return collectionModel;
    }

}
