package com.algaworks.algafood.api.assembler.restaurante;

import com.algaworks.algafood.api.model.dtooutput.RestauranteDtoOutput;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDtoOutput toDtoOutput(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDtoOutput.class);
    }

    public List<RestauranteDtoOutput> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toDtoOutput(restaurante))
                .collect(Collectors.toList());
    }

}
