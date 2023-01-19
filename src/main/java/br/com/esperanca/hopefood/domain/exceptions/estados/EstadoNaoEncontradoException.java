package br.com.esperanca.hopefood.domain.exceptions.estados;

import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

  private static final long serialVersionUID = 1L;

  private static final String ESTADO_NAO_ENCONTRADO =
    "Estado de id %d não encontrado";

  public EstadoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public EstadoNaoEncontradoException(Long id) {
    this(String.format(ESTADO_NAO_ENCONTRADO, id));
  }
}
