package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
//        var modelMapper = new ModelMapper();
//
//        modelMapper.createTypeMap(Restaurante.class, RestauranteDtoOutput.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteDtoOutput::setPrecoFrete);
//
//        return modelMapper;
        return new ModelMapper();
    }

}
