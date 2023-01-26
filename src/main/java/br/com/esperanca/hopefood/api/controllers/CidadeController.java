package br.com.esperanca.hopefood.api.controllers;

import br.com.esperanca.hopefood.api.exceptionhandler.Erro;
import br.com.esperanca.hopefood.domain.entities.Cidade;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.esperanca.hopefood.domain.exceptions.NegocioException;
import br.com.esperanca.hopefood.domain.exceptions.estados.EstadoNaoEncontradoException;
import br.com.esperanca.hopefood.domain.services.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cidades")
public class CidadeController {

  private CidadeService cidadeService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Cidade> buscarTodas() {
    return cidadeService.buscarTodas();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Cidade buscarPorId(@PathVariable Long id) {
    return cidadeService.buscarPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
    try {
      return cidadeService.salvar(cidade);
    }
    catch (EstadoNaoEncontradoException estadoNaoEncontradoException) {
      throw new NegocioException(estadoNaoEncontradoException.getMessage(),
        estadoNaoEncontradoException
      );
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Cidade atualizar(@RequestBody @Valid Cidade cidade,
      @PathVariable Long id) {

    try {
      Cidade cidadeAtual = cidadeService.buscarPorId(id);

      BeanUtils.copyProperties(cidade, cidadeAtual,
        "id", "dataCadastro");

      return cidadeService.salvar(cidadeAtual);
    }
    catch (EstadoNaoEncontradoException estadoNaoEncontradoException) {
      throw new NegocioException(estadoNaoEncontradoException.getMessage()
      );
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long id) {
    cidadeService.remover(id);
  }
}
