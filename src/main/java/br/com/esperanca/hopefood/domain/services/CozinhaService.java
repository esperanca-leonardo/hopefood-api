package br.com.esperanca.hopefood.domain.services;

import br.com.esperanca.hopefood.domain.entities.Cozinha;
import br.com.esperanca.hopefood.domain.exceptions.cozinhas.CozinhaEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.cozinhas.CozinhaNaoEncontradaException;
import br.com.esperanca.hopefood.domain.repositories.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CozinhaService {

  private CozinhaRepository cozinhaRepository;

  public List<Cozinha> buscarTodas() {
    return cozinhaRepository.findAll();
  }

  public Cozinha buscarPorId(Long id) {
    return cozinhaRepository.findById(id)
      .orElseThrow(() -> new CozinhaNaoEncontradaException(id));
  }

  public Cozinha salvar(Cozinha cozinha) {
    return cozinhaRepository.save(cozinha);
  }

  public void remover(Long id) {
    try {
      cozinhaRepository.deleteById(id);
    }
    catch (EmptyResultDataAccessException exception) {
      throw new CozinhaNaoEncontradaException(id);
    }
    catch (DataIntegrityViolationException exception) {
      throw new CozinhaEmUsoException(id);
    }
  }
}
