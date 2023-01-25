package br.com.esperanca.hopefood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Erro {

  private Integer status;

  @JsonProperty("type")
  private String tipo;

  @JsonProperty("title")
  private String titulo;

  @JsonProperty("description")
  private String descricao;

  @JsonProperty("timestamp")
  private LocalDateTime dataHora;

  private List<Campo> campos;
}
