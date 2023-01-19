package br.com.esperanca.hopefood.domain.exceptions.cidades;

import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;

public class CidadeEmUsoException extends EntidadeEmUsoException {

  private static final long serialVersionUID = 1L;

  public static final String CIDADE_EM_USO =
    "Cidade de id %d não pode ser removida, pois está em uso";

  public CidadeEmUsoException(String mensagem) {
    super(mensagem);
  }

  public CidadeEmUsoException(Long id) {
    this(String.format(CIDADE_EM_USO, id));
  }
}
