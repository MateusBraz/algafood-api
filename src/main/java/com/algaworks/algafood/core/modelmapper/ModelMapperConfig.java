package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.dto.input.ItemPedidoDtoInput;
import com.algaworks.algafood.api.model.dto.output.EnderecoDtoOutput;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
//
//        modelMapper.createTypeMap(Restaurante.class, RestauranteDtoOutput.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteDtoOutput::setPrecoFrete);

        modelMapper.createTypeMap(ItemPedidoDtoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderencoToEnderecoDtoOutputTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDtoOutput.class);
        enderencoToEnderecoDtoOutputTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoDtoOutputDest, value) -> enderecoDtoOutputDest.getCidade().setEstado(value));

        return modelMapper;
    }

}
