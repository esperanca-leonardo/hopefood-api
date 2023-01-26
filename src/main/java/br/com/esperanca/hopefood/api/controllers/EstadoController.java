package br.com.esperanca.hopefood.api.controllers;

import br.com.esperanca.hopefood.domain.entities.Estado;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.esperanca.hopefood.domain.services.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/estados")
public class EstadoController {

  private EstadoService estadoService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Estado> buscarTodos() {
    return estadoService.buscarTodos();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Estado buscarPorId(@PathVariable Long id) {
    return estadoService.buscarPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Estado adicionar(@RequestBody @Valid Estado estado) {
    return estadoService.salvar(estado);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Estado atualizar(@RequestBody @Valid Estado estado,
      @PathVariable Long id) {
    Estado estadoAtual = estadoService.buscarPorId(id);

    BeanUtils.copyProperties(estado, estadoAtual,
      "id", "dataCadastro");

    return estadoService.salvar(estadoAtual);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long id) {
    estadoService.remover(id);
  }
}
