package br.com.esperanca.hopefood.api.exceptionhandler.enuns;

import lombok.Getter;

@Getter
public enum TipoErro {

  TIPO_DE_DADO_INCORRETO("/tipo-de-dado-incorreto",
    "Tipo de dado incorreto"),

  PROPRIEDADE_NAO_ENCONTRADA("/propriedade-nao-encontrada",
    "Propriedade não encontrada"),

  PROPRIEDADE_IGNORADA("/propriedade-ignorada",
    "Propriedade ignorada"),

  ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada",
    "Entidade não encontrada"),

  ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),

  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),

  PROBLEMA_DE_SINTAXE("/problema-sintaxe", "Problema de sintaxe");

  private String uri;

  private String titulo;

  TipoErro(String uri, String titulo) {
    this.uri = "https://hopefood.com.br" + uri;
    this.titulo = titulo;
  }
}