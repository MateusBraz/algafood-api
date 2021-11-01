package com.algaworks.algafood.api.assembler.cidade;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.dto.output.CidadeDtoOutput;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CidadeDtoAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeDtoAssembler() {
        super(CidadeController.class, CidadeDtoOutput.class);
    }

    @Override
    public CidadeDtoOutput toModel(Cidade cidade) {
//        CidadeDtoOutput cidadeDtoOutput = modelMapper.map(cidade, CidadeDtoOutput.class);
//        cidadeDtoOutput.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(cidadeDtoOutput.getId())).withSelfRel());

        CidadeDtoOutput cidadeDtoOutput = createModelWithId(cidade.getId(), cidade);
        modelMapper.map(cidade, cidadeDtoOutput);
        cidadeDtoOutput.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).listar()).withRel("cidades"));
        cidadeDtoOutput.getEstado().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(cidadeDtoOutput.getEstado().getId())).withSelfRel());

        return cidadeDtoOutput;
    }

    @Override
    public CollectionModel<CidadeDtoOutput> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
                .add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
    }

    //    public List<CidadeDtoOutput> toCollectionDtoOutput(List<Cidade> cidades) {
//        return cidades.stream()
//                .map(cidade -> toModel(cidade))
//                .collect(Collectors.toList());
//    }

}
