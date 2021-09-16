package com.algaworks.algafood.api.assembler.usuario;

import com.algaworks.algafood.api.model.dto.output.UsuarioDtoOutput;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDtoOutput toDtoOutput(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDtoOutput.class);
    }

    public List<UsuarioDtoOutput> toCollectionDtoOutput(Collection<Usuario> usuarios) {
        return usuarios.stream()
                .map(grupo -> toDtoOutput(grupo))
                .collect(Collectors.toList());
    }

}
