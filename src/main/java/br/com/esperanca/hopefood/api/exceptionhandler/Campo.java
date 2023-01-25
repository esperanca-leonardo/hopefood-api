package br.com.esperanca.hopefood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Campo {

  @JsonProperty("name")
  private String nome;

  @JsonProperty("message")
  private String mensagem;
}
