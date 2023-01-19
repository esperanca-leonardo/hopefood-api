package br.com.esperanca.hopefood.domain.exceptions.cozinhas;

import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

  private static final long serialVersionUID = 1L;

  public static final String COZINHA_NAO_ENCONTRADA =
    "Cozinha de código %d não encontrada";

  public CozinhaNaoEncontradaException(String mensagem) {
    super(mensagem);
  }

  public CozinhaNaoEncontradaException(Long id) {
    this(String.format(COZINHA_NAO_ENCONTRADA, id));
  }
}
