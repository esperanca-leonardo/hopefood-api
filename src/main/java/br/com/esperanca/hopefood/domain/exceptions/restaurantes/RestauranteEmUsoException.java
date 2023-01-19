package br.com.esperanca.hopefood.domain.exceptions.restaurantes;

import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;

public class RestauranteEmUsoException extends EntidadeEmUsoException {

  private static final long serialVersionUID = 1L;

  public static final String RESTAURANTE_EM_USO =
    "Restaurante de id %d não pode ser removido, pois esta em uso";

  public RestauranteEmUsoException(String mensagem) {
    super(mensagem);
  }

  public RestauranteEmUsoException(Long id) {
    this(String.format(RESTAURANTE_EM_USO, id));
  }
}
