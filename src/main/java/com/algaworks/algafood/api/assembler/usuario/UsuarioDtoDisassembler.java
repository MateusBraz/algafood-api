package com.algaworks.algafood.api.assembler.usuario;

import com.algaworks.algafood.api.model.dtoinput.UsuarioDtoInput;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainModel(UsuarioDtoInput usuarioDtoInput) {
        return modelMapper.map(usuarioDtoInput, Usuario.class);
    }

    public void copyToDomainModel(UsuarioDtoInput usuarioDtoInput, Usuario usuario) {
        modelMapper.map(usuarioDtoInput, usuario);
    }

}
