package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.restaurante.RestauranteApenasDtoAssembler;
import com.algaworks.algafood.api.assembler.restaurante.RestauranteBasicoDtoAssembler;
import com.algaworks.algafood.api.assembler.restaurante.RestauranteDtoAssembler;
import com.algaworks.algafood.api.assembler.restaurante.RestauranteDtoDisassembler;
import com.algaworks.algafood.api.model.dto.input.RestauranteDtoInput;
import com.algaworks.algafood.api.model.dto.output.RestauranteDtoOutput;
import com.algaworks.algafood.api.model.dto.output.resumo.RestauranteApenasNomeDtoOutput;
import com.algaworks.algafood.api.model.dto.output.resumo.RestauranteBasicoDtoOutput;
import com.algaworks.algafood.api.openapi.controller.RestauranteControllerOpenApi;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteDtoAssembler restauranteDtoAssembler;

    @Autowired
    private RestauranteBasicoDtoAssembler restauranteBasicoDtoAssembler;

    @Autowired
    private RestauranteApenasDtoAssembler restauranteApenasDtoAssembler;

    @Autowired
    private RestauranteDtoDisassembler restauranteDtoDisassembler;

    //    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public CollectionModel<RestauranteBasicoDtoOutput> listar() {
        List<Restaurante> restaurantes = restauranteRepository.buscarTodos();
        return restauranteBasicoDtoAssembler.toCollectionModel(restaurantes);
    }

    //    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public CollectionModel<RestauranteApenasNomeDtoOutput> listarResumido() {
        List<Restaurante> restaurantes = restauranteRepository.buscarTodos();
        return restauranteApenasDtoAssembler.toCollectionModel(restaurantes);
    }

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
//        List<Restaurante> restaurantes = restauranteRepository.buscarTodos();
//        List<RestauranteDtoOutput> restaurantesDtoOutput = restauranteDtoAssembler.toCollectionDtoOutput(restaurantes);
//
//        MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesDtoOutput);
//
//        restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//
//        if ("apenas-nome".equals(projecao)) {
//            restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//        } else if ("completo".equals(projecao)) {
//            restaurantesWrapper.setSerializationView(null);
//        }
//        return restaurantesWrapper;
//    }

//    @GetMapping
//    public List<RestauranteDtoOutput> listar() {
//        List<Restaurante> restaurantes = restauranteRepository.buscarTodos();
//        return restauranteDtoAssembler.toCollectionDtoOutput(restaurantes);
//    }
//
//    @JsonView(RestauranteView.Resumo.class)
//    @GetMapping(params = "projecao=resumo")
//    public List<RestauranteDtoOutput> listarResumido() {
//        return listar();
//    }
//
//    @JsonView(RestauranteView.ApenasNome.class)
//    @GetMapping(params = "projecao=apenas-nome")
//    public List<RestauranteDtoOutput> listarApenasNome() {
//        return listar();
//    }

    @GetMapping("/{id}")
    public RestauranteDtoOutput buscar(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(id);
        return restauranteDtoAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDtoOutput adicionar(@RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
        try {
            Restaurante restaurante = restauranteDtoDisassembler.toDomainModel(restauranteDtoInput);
            restaurante = restauranteService.salvar(restaurante);
            return restauranteDtoAssembler.toModel(restaurante);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteDtoOutput atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
        try {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
            restauranteDtoDisassembler.copyToDomainModel(restauranteDtoInput, restauranteAtual);
//            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
            restauranteAtual = restauranteService.salvar(restauranteAtual);
            return restauranteDtoAssembler.toModel(restauranteAtual);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        restauranteService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        restauranteService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long id) {
        restauranteService.abrir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long id) {
        restauranteService.fechar(id);
        return ResponseEntity.noContent().build();
    }

}
