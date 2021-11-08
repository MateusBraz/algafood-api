package com.algaworks.algafood.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeDtoOutput extends RepresentationModel<CidadeDtoOutput> {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Belém")
    private String nome;
    private EstadoDtoOutput estado;

}
