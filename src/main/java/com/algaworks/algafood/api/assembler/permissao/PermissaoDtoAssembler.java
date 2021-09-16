package com.algaworks.algafood.api.assembler.permissao;

import com.algaworks.algafood.api.model.dto.output.PermissaoDtoOutput;
import com.algaworks.algafood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDtoOutput toDtoOutput(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoDtoOutput.class);
    }

    public List<PermissaoDtoOutput> toCollectionDtoOutput(Collection<Permissao> permissoes) {
        return permissoes.stream()
                .map(permissao -> toDtoOutput(permissao))
                .collect(Collectors.toList());
    }

}
