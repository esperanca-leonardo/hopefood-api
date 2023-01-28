package br.com.esperanca.hopefood.api.controllers;

import br.com.esperanca.hopefood.domain.entities.Cozinha;
import br.com.esperanca.hopefood.domain.services.CozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cozinhas")
public class CozinhaController {

  private CozinhaService cozinhaService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Cozinha> buscarTodas() {
    return cozinhaService.buscarTodas();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Cozinha buscarPorId(@PathVariable Long id) {
    return cozinhaService.buscarPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {
    return cozinhaService.salvar(cozinha);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Cozinha atualizar(@RequestBody @Valid Cozinha cozinha,
      @PathVariable Long id) {

    Cozinha cozinhaAtual = cozinhaService.buscarPorId(id);

    BeanUtils.copyProperties(cozinha, cozinhaAtual,
      "id", "dataCadastro");

    return cozinhaService.salvar(cozinhaAtual);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long id) {
    cozinhaService.remover(id);
  }
}
