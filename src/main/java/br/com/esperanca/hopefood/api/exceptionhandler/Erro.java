package br.com.esperanca.hopefood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Erro {
  private LocalDateTime dataHora;
  private String mensagem;
}
