package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.restaurante.RestauranteDtoAssembler;
import com.algaworks.algafood.api.assembler.restaurante.RestauranteDtoDisassebler;
import com.algaworks.algafood.api.model.dtoinput.RestauranteDtoInput;
import com.algaworks.algafood.api.model.dtooutput.RestauranteDtoOutput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteDtoAssembler restauranteDtoAssembler;

    @Autowired
    private RestauranteDtoDisassebler restauranteDtoDisassebler;

    @GetMapping
    public List<RestauranteDtoOutput> listar() {
        List<Restaurante> restaurantes = restauranteRepository.buscarTodos();
        return restauranteDtoAssembler.toCollectionModel(restaurantes);
    }

    @GetMapping("/{id}")
    public RestauranteDtoOutput buscar(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(id);
        return restauranteDtoAssembler.toDtoOutput(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDtoOutput adicionar(@RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
        try {
            Restaurante restaurante = restauranteDtoDisassebler.toDomainModel(restauranteDtoInput);
            restaurante = restauranteService.salvar(restaurante);
            return restauranteDtoAssembler.toDtoOutput(restaurante);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
        try {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
            restauranteDtoDisassebler.copyToDomainModel(restauranteDtoInput, restauranteAtual);
//            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
            restauranteAtual = restauranteService.salvar(restauranteAtual);
            return restauranteDtoAssembler.toDtoOutput(restauranteAtual);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

}
