package com.algaworks.algafood.api.assembler.restaurante;

import com.algaworks.algafood.api.model.dtoinput.RestauranteDtoInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDtoDisassebler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainModel(RestauranteDtoInput restauranteDtoInput) {
        return modelMapper.map(restauranteDtoInput, Restaurante.class);
    }

    public void copyToDomainModel(RestauranteDtoInput restauranteDtoInput, Restaurante restaurante) {
        //Para evitar org.hibernate.HibernateException: identifier of
        // an instance of com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());
        modelMapper.map(restauranteDtoInput, restaurante);
    }

}
