package br.com.esperanca.hopefood.domain.services;

import br.com.esperanca.hopefood.domain.entities.Cozinha;
import br.com.esperanca.hopefood.domain.entities.Restaurante;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.esperanca.hopefood.domain.exceptions.restaurantes.RestauranteEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.restaurantes.RestauranteNaoEncontradoException;
import br.com.esperanca.hopefood.domain.repositories.CozinhaRepository;
import br.com.esperanca.hopefood.domain.repositories.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestauranteService {

  private RestauranteRepository restauranteRepository;

  private CozinhaService cozinhaService;

  public List<Restaurante> buscarTodos() {
    return restauranteRepository.findAll();
  }

  public Restaurante buscarPorId(Long id) {
    return restauranteRepository
      .findById(id)
      .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
  }

  public Restaurante salvar(Restaurante restaurante) {
    Long cozinhaId = restaurante.getCozinha().getId();

    Cozinha cozinha = cozinhaService.buscarPorId(cozinhaId);

    restaurante.setCozinha(cozinha);

    return restauranteRepository.save(restaurante);
  }

  public void remover(Long id) {
    try {
      restauranteRepository.deleteById(id);
    }
    catch (EmptyResultDataAccessException emptyResultDataAccessException) {
      throw new RestauranteNaoEncontradoException(id);
    }
    catch (DataIntegrityViolationException dataIntegrityViolationException) {
      throw new RestauranteEmUsoException(id);
    }
  }
}
