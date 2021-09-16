package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.produto.ProdutoDtoAssembler;
import com.algaworks.algafood.api.assembler.produto.ProdutoDtoDisassembler;
import com.algaworks.algafood.api.model.dto.input.ProdutoDtoInput;
import com.algaworks.algafood.api.model.dto.output.ProdutoDtoOutput;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.ProdutoService;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoDtoAssembler produtoDtoAssembler;

    @Autowired
    private ProdutoDtoDisassembler produtoDtoDisassembler;

    @GetMapping
    public List<ProdutoDtoOutput> listar(@PathVariable Long restauranteId, @RequestParam(required = false) boolean incluirInativos) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        List<Produto> todosProdutos = null;

        if (incluirInativos) {
            todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
        } else {
            todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoDtoAssembler.toCollectionDtoOutput(todosProdutos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoDtoOutput buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
        return produtoDtoAssembler.toDtoOutput(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDtoOutput adicionar(@PathVariable Long restauranteId,
                                      @RequestBody @Valid ProdutoDtoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoDtoDisassembler.toDomainModel(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtoService.salvar(produto);

        return produtoDtoAssembler.toDtoOutput(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoDtoOutput atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                      @RequestBody @Valid ProdutoDtoInput produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);

        produtoDtoDisassembler.copyToDomainModel(produtoInput, produtoAtual);

        produtoAtual = produtoService.salvar(produtoAtual);

        return produtoDtoAssembler.toDtoOutput(produtoAtual);
    }

}
