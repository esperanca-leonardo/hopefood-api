package br.com.esperanca.hopefood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Campo {

  private String nome;

  private String mensagem;
}
