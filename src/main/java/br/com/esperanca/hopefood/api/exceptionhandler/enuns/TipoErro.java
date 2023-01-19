package br.com.esperanca.hopefood.api.exceptionhandler.enuns;

import lombok.Getter;

@Getter
public enum TipoErro {

  ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada",
    "Entidade não encontrada"),

  ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),

  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

  private String uri;

  private String titulo;

  TipoErro(String uri, String titulo) {
    this.uri = "https://hopefood.com.br" + uri;
    this.titulo = titulo;
  }
}