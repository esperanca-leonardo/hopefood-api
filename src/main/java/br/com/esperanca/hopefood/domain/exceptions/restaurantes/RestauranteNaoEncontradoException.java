package br.com.esperanca.hopefood.domain.exceptions.restaurantes;

import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

  private static final long serialVersionUID = 1L;

  public static final String RESTAURANTE_NAO_ENCONTRADO =
    "Restaurante de id %d não encontrado";

  public RestauranteNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public RestauranteNaoEncontradoException(Long id) {
    this(String.format(RESTAURANTE_NAO_ENCONTRADO, id));
  }
}
