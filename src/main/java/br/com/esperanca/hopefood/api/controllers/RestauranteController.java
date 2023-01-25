package br.com.esperanca.hopefood.api.controllers;

import br.com.esperanca.hopefood.domain.entities.Restaurante;
import br.com.esperanca.hopefood.domain.exceptions.NegocioException;
import br.com.esperanca.hopefood.domain.exceptions.cozinhas.CozinhaNaoEncontradaException;
import br.com.esperanca.hopefood.domain.services.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurantes")
public class RestauranteController {

  private RestauranteService restauranteService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Restaurante> buscarTodos() {
    return restauranteService.buscarTodos();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Restaurante buscarPorId(@PathVariable Long id) {
    return restauranteService.buscarPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
    try {
      return restauranteService.salvar(restaurante);
    }
    catch (CozinhaNaoEncontradaException exception) {
      throw new NegocioException(exception.getMessage(),exception);
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Restaurante atualizar(@PathVariable Long id,
      @RequestBody Restaurante restaurante) {

    try {
      Restaurante restauranteAtual = restauranteService.buscarPorId(id);

      BeanUtils.copyProperties(restaurante, restauranteAtual,
        "id", "formaPagamento", "endereco", "dataCadastro",
        "produto"
      );
      return restauranteService.salvar(restauranteAtual);
    }
    catch (CozinhaNaoEncontradaException exception) {
      throw new NegocioException(exception.getMessage(), exception);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long id) {
    restauranteService.remover(id);
  }
}
