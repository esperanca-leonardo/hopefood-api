package br.com.esperanca.hopefood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Erro {

  private Integer status;

  @JsonProperty("type")
  private String tipo;

  @JsonProperty("title")
  private String titulo;

  @JsonProperty("detail")
  private String detalhe;
}
