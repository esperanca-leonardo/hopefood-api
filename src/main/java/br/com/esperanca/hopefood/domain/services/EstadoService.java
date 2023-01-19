package br.com.esperanca.hopefood.domain.services;

import br.com.esperanca.hopefood.domain.entities.Estado;
import br.com.esperanca.hopefood.domain.exceptions.cidades.CidadeEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.estados.EstadoEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.estados.EstadoNaoEncontradoException;
import br.com.esperanca.hopefood.domain.repositories.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadoService {

  private EstadoRepository estadoRepository;

  public List<Estado> buscarTodos() {
    return estadoRepository.findAll();
  }

  public Estado buscarPorId(Long id) {
    return estadoRepository
      .findById(id)
      .orElseThrow(() -> new EstadoNaoEncontradoException(id));
  }

  public Estado salvar(Estado estado) {
    return estadoRepository.save(estado);
  }

  public void remover(Long id) {
    try {
      estadoRepository.deleteById(id);
    }
    catch (EmptyResultDataAccessException emptyResultDataAccessException) {
      throw new EstadoNaoEncontradoException(id);
    }
    catch (DataIntegrityViolationException dataIntegrityViolationException) {
      throw new EstadoEmUsoException(id);
    }
  }
}
