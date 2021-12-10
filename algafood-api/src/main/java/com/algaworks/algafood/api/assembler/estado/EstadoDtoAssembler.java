package com.algaworks.algafood.api.assembler.estado;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.dto.output.EstadoDtoOutput;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class EstadoDtoAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public EstadoDtoAssembler() {
        super(EstadoController.class, EstadoDtoOutput.class);
    }

    @Override
    public EstadoDtoOutput toModel(Estado estado) {
        EstadoDtoOutput estadoDtoOutput = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoDtoOutput);
        estadoDtoOutput.add(algaLinks.linkToEstados("estados"));
        return estadoDtoOutput;
    }

    @Override
    public CollectionModel<EstadoDtoOutput> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToEstados());
    }

    //    public List<EstadoDtoOutput> toCollectionDtoOutput(List<Estado> estados) {
//        return estados.stream()
//                .map(estado -> toModel(estado))
//                .collect(Collectors.toList());
//    }

}
