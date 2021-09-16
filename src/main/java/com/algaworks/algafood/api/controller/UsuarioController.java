package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.usuario.UsuarioDtoAssembler;
import com.algaworks.algafood.api.assembler.usuario.UsuarioDtoDisassembler;
import com.algaworks.algafood.api.model.dto.input.SenhaDtoInput;
import com.algaworks.algafood.api.model.dto.input.UsuarioComSenhaDtoInput;
import com.algaworks.algafood.api.model.dto.input.UsuarioDtoInput;
import com.algaworks.algafood.api.model.dto.output.UsuarioDtoOutput;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDtoAssembler usuarioDtoAssembler;

    @Autowired
    private UsuarioDtoDisassembler usuarioDtoDisassembler;

    @GetMapping
    public List<UsuarioDtoOutput> listar() {
        List<Usuario> todasUsuarios = usuarioRepository.findAll();
        return usuarioDtoAssembler.toCollectionDtoOutput(todasUsuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDtoOutput buscar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return usuarioDtoAssembler.toDtoOutput(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDtoOutput adicionar(@RequestBody @Valid UsuarioComSenhaDtoInput usuarioInput) {
        Usuario usuario = usuarioDtoDisassembler.toDomainModel(usuarioInput);
        usuario = usuarioService.salvar(usuario);
        return usuarioDtoAssembler.toDtoOutput(usuario);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDtoOutput atualizar(@PathVariable Long usuarioId,
                                      @RequestBody @Valid UsuarioDtoInput usuarioInput) {
        Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
        usuarioDtoDisassembler.copyToDomainModel(usuarioInput, usuarioAtual);
        usuarioAtual = usuarioService.salvar(usuarioAtual);
        return usuarioDtoAssembler.toDtoOutput(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaDtoInput senhaDtoInput) {
        usuarioService.alterarSenha(usuarioId, senhaDtoInput.getSenhaAtual(), senhaDtoInput.getNovaSenha());
    }

}
