package br.com.esperanca.hopefood.domain.exceptions.cozinhas;

import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;

public class CozinhaEmUsoException extends EntidadeEmUsoException {

  private static final long serialVersionUID = 1L;

  public static final String COZINHA_EM_USO =
    "Cozinha de código %d não pode ser removida, pois está em uso";

  public CozinhaEmUsoException(String mensagem) {
    super(mensagem);
  }

  public CozinhaEmUsoException(Long id) {
    this(String.format(COZINHA_EM_USO, id));
  }
}
