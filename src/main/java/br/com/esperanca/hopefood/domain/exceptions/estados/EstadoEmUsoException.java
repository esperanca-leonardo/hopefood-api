package br.com.esperanca.hopefood.domain.exceptions.estados;

import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;

public class EstadoEmUsoException extends EntidadeEmUsoException {

  private static final long serialVersionUID = 1L;

  public static final String ESTADO_EM_USO =
    "Estado de id %d não pode ser removido, pois esta em uso";

  public EstadoEmUsoException(String mensagem) {
    super(mensagem);
  }

  public EstadoEmUsoException(Long id) {
    this(String.format(ESTADO_EM_USO, id));
  }
}
