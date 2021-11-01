package com.algaworks.algafood.api.assembler.cozinha;

import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.model.dto.output.CozinhaDtoOutput;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDtoAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDtoAssembler() {
        super(CozinhaController.class, CozinhaDtoOutput.class);
    }

    @Override
    public CozinhaDtoOutput toModel(Cozinha cozinha) {
        CozinhaDtoOutput cozinhaDtoOutput = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaDtoOutput);

        cozinhaDtoOutput.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel("cozinhas"));

        return cozinhaDtoOutput;

    }

}
