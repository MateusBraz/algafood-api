package com.algaworks.algafood.api.assembler.usuario;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.model.dto.output.UsuarioDtoOutput;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public UsuarioDtoAssembler() {
        super(UsuarioController.class, UsuarioDtoOutput.class);
    }

    @Override
    public UsuarioDtoOutput toModel(Usuario usuario) {
        UsuarioDtoOutput usuarioDtoOutput = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioDtoOutput);
        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            usuarioDtoOutput.add(algaLinks.linkToUsuarios("usuarios"));
            usuarioDtoOutput.add(algaLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
        }
        return usuarioDtoOutput;
    }

    @Override
    public CollectionModel<UsuarioDtoOutput> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToUsuarios());
    }

    //    public List<UsuarioDtoOutput> toCollectionDtoOutput(Collection<Usuario> usuarios) {
//        return usuarios.stream()
//                .map(grupo -> toModel(grupo))
//                .collect(Collectors.toList());
//    }

}
