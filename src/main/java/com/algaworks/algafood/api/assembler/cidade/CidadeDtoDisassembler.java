package com.algaworks.algafood.api.assembler.cidade;

import com.algaworks.algafood.api.model.dtoinput.CidadeDtoInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainModel(CidadeDtoInput cidadeDtoInput) {
        return modelMapper.map(cidadeDtoInput, Cidade.class);
    }

    public void copyToDomainModel(CidadeDtoInput cidadeDtoInput, Cidade cidade) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.algaworks.algafood.domain.model.Estado was altered from 1 to 2
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeDtoInput, cidade);
    }

}
