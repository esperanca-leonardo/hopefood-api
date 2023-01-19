package br.com.esperanca.hopefood.domain.services;

import br.com.esperanca.hopefood.domain.entities.Cidade;
import br.com.esperanca.hopefood.domain.entities.Estado;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.esperanca.hopefood.domain.exceptions.cidades.CidadeEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.cidades.CidadeNaoEncontradaException;
import br.com.esperanca.hopefood.domain.repositories.CidadeRepository;
import br.com.esperanca.hopefood.domain.repositories.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CidadeService {

  private CidadeRepository cidadeRepository;

  private EstadoService estadoService;

  public List<Cidade> buscarTodas() {
    return cidadeRepository.findAll();
  }

  public Cidade buscarPorId(Long id) {
    return cidadeRepository
      .findById(id)
      .orElseThrow(() -> new CidadeNaoEncontradaException(id));
  }

  public Cidade salvar(Cidade cidade) {
    Long estadoId = cidade.getEstado().getId();

    Estado estado = estadoService.buscarPorId(estadoId);

    cidade.setEstado(estado);

    return cidadeRepository.save(cidade);
  }

  public void remover(Long id) {
    try {
      cidadeRepository.deleteById(id);
    }
    catch (EmptyResultDataAccessException emptyResultDataAccessException) {
      throw new CidadeNaoEncontradaException(id);
    }
    catch (DataIntegrityViolationException dataIntegrityViolationException) {
      throw new CidadeEmUsoException(id);
    }
  }
}
