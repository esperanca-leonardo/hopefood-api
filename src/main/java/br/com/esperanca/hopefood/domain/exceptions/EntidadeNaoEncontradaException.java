package br.com.esperanca.hopefood.domain.exceptions;

public class EntidadeNaoEncontradaException extends NegocioException {

  private static final long serialVersionUID = 1L;

  public EntidadeNaoEncontradaException(String mensagem) {
    super(mensagem);
  }
}
