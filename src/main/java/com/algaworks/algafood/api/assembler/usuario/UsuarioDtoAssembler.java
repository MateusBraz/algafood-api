package com.algaworks.algafood.api.assembler.usuario;

import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;
import com.algaworks.algafood.api.model.dto.output.UsuarioDtoOutput;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDtoAssembler() {
        super(UsuarioController.class, UsuarioDtoOutput.class);
    }

    @Override
    public UsuarioDtoOutput toModel(Usuario usuario) {
        UsuarioDtoOutput usuarioDtoOutput = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioDtoOutput);

        usuarioDtoOutput.add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withRel("usuarios"));
        usuarioDtoOutput.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).listar(usuario.getId())).withRel("grupos-usuario"));

        return usuarioDtoOutput;
    }

    @Override
    public CollectionModel<UsuarioDtoOutput> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withSelfRel());
    }

    //    public List<UsuarioDtoOutput> toCollectionDtoOutput(Collection<Usuario> usuarios) {
//        return usuarios.stream()
//                .map(grupo -> toModel(grupo))
//                .collect(Collectors.toList());
//    }

}
