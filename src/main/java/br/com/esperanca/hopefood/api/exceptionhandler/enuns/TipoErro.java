package br.com.esperanca.hopefood.api.exceptionhandler.enuns;

import lombok.Getter;

@Getter
public enum TipoErro {

  DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),

  ERRO_DE_SISTEMA("/erro-sistema", "Erro no Sistema"),

  PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),

  TIPO_DE_DADO_INCORRETO("/tipo-de-dado-incorreto",
    "Tipo de dado incorreto"),

  PROPRIEDADE_NAO_ENCONTRADA("/propriedade-nao-encontrada",
    "Propriedade não encontrada"),

  PROPRIEDADE_IGNORADA("/propriedade-ignorada",
    "Propriedade ignorada"),

  RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado",
    "Recurso não encontrado"),

  RECURSO_EM_USO("/recurso-em-uso", "Recurso em uso"),

  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),

  PROBLEMA_DE_SINTAXE("/problema-sintaxe", "Problema de sintaxe");

  private String uri;

  private String titulo;

  TipoErro(String uri, String titulo) {
    this.uri = "https://hopefood.com.br" + uri;
    this.titulo = titulo;
  }
}