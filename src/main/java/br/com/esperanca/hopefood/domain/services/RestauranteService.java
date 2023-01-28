package br.com.esperanca.hopefood.domain.services;

import br.com.esperanca.hopefood.domain.entities.Restaurante;
import br.com.esperanca.hopefood.domain.exceptions.restaurantes.RestauranteEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.restaurantes.RestauranteNaoEncontradoException;
import br.com.esperanca.hopefood.domain.repositories.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestauranteService {

  private RestauranteRepository restauranteRepository;

  private CozinhaService cozinhaService;

  public List<Restaurante> buscarTodos() {
    return restauranteRepository.findAll();
  }

  public Restaurante buscarPorId(Long id) {
    return restauranteRepository.findById(id)
      .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
  }

  public Restaurante salvar(Restaurante restaurante) {

    Long cozinhaId = restaurante.getCozinha().getId();
    var cozinha = cozinhaService.buscarPorId(cozinhaId);

    restaurante.setCozinha(cozinha);

    return restauranteRepository.save(restaurante);
  }

  public void remover(Long id) {
    try {
      restauranteRepository.deleteById(id);
    }
    catch (EmptyResultDataAccessException exception) {
      throw new RestauranteNaoEncontradoException(id);
    }
    catch (DataIntegrityViolationException exception) {
      throw new RestauranteEmUsoException(id);
    }
  }
}
