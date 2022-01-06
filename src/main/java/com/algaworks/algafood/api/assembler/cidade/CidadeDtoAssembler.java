package com.algaworks.algafood.api.assembler.cidade;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.model.dto.output.CidadeDtoOutput;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CidadeDtoAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDtoOutput> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public CidadeDtoAssembler() {
        super(CidadeController.class, CidadeDtoOutput.class);
    }

    @Override
    public CidadeDtoOutput toModel(Cidade cidade) {
//        CidadeDtoOutput cidadeDtoOutput = modelMapper.map(cidade, CidadeDtoOutput.class);
//        cidadeDtoOutput.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(cidadeDtoOutput.getId())).withSelfRel());

        CidadeDtoOutput cidadeDtoOutput = createModelWithId(cidade.getId(), cidade);
        modelMapper.map(cidade, cidadeDtoOutput);

        if (algaSecurity.podeConsultarCidades()) {
            cidadeDtoOutput.add(algaLinks.linkToCidades("cidades"));
        }

        if (algaSecurity.podeConsultarEstados()) {
            cidadeDtoOutput.getEstado().add(algaLinks.linkToEstado(cidadeDtoOutput.getEstado().getId()));
        }

        return cidadeDtoOutput;
    }

    @Override
    public CollectionModel<CidadeDtoOutput> toCollectionModel(Iterable<? extends Cidade> entities) {
        CollectionModel<CidadeDtoOutput> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarCidades()) {
            collectionModel.add(algaLinks.linkToCidades());
        }

        return collectionModel;
    }

    //    public List<CidadeDtoOutput> toCollectionDtoOutput(List<Cidade> cidades) {
//        return cidades.stream()
//                .map(cidade -> toModel(cidade))
//                .collect(Collectors.toList());
//    }

}
