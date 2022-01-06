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
import com.algaworks.algafood.core.security.CheckSecurity;
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
    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<RestauranteBasicoDtoOutput> listar() {
        List<Restaurante> restaurantes = restauranteRepository.buscarTodos();
        return restauranteBasicoDtoAssembler.toCollectionModel(restaurantes);
    }

    //    @JsonView(RestauranteView.ApenasNome.class)
    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
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

    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping("/{restauranteId}")
    public RestauranteDtoOutput buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        return restauranteDtoAssembler.toModel(restaurante);
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
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

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PutMapping("/{restauranteId}")
    public RestauranteDtoOutput atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
        try {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
            restauranteDtoDisassembler.copyToDomainModel(restauranteDtoInput, restauranteAtual);
//            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
            restauranteAtual = restauranteService.salvar(restauranteAtual);
            return restauranteDtoAssembler.toModel(restauranteAtual);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
        restauranteService.inativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @Override
    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
        restauranteService.abrir(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @Override
    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
        restauranteService.fechar(restauranteId);
        return ResponseEntity.noContent().build();
    }

}
