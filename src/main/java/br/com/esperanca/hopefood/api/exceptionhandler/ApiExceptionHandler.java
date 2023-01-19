package br.com.esperanca.hopefood.api.exceptionhandler;

import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.esperanca.hopefood.domain.exceptions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> tratarNegocioException(
    NegocioException negocioException) {

    Erro erro = Erro.builder()
      .dataHora(LocalDateTime.now())
      .mensagem(negocioException.getMessage())
      .build();

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(erro);
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
    EntidadeNaoEncontradaException entidadeNaoEncontradaException) {

    Erro erro = Erro.builder()
      .dataHora(LocalDateTime.now())
      .mensagem(entidadeNaoEncontradaException.getMessage())
      .build();

    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(erro);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> tratarEntidadeEmUsoException(
    EntidadeEmUsoException entidadeEmUsoException) {

    Erro erro = Erro.builder()
      .dataHora(LocalDateTime.now())
      .mensagem(entidadeEmUsoException.getMessage())
      .build();

    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(erro);
  }
}