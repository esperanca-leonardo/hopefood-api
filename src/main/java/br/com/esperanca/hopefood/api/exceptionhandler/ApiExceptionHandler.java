package br.com.esperanca.hopefood.api.exceptionhandler;

import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.esperanca.hopefood.domain.exceptions.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                           Object body,
                                                           HttpHeaders headers,
                                                           HttpStatus status,
                                                           WebRequest request) {

    if (body == null) {
      body = Erro.builder()
        .dataHora(LocalDateTime.now())
        .mensagem(status.getReasonPhrase())
        .build();
    }
    else if (body instanceof String) {
      body = Erro.builder()
        .dataHora(LocalDateTime.now())
        .mensagem((String) body)
        .build();
    }
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> tratarNegocioException(
    NegocioException negocioException, WebRequest webRequest) {

    return handleExceptionInternal(
      negocioException,
      negocioException.getMessage(),
      new HttpHeaders(),
      HttpStatus.BAD_REQUEST,
      webRequest
    );
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
    EntidadeNaoEncontradaException entidadeNaoEncontradaException,
    WebRequest webRequest) {

    return handleExceptionInternal(
      entidadeNaoEncontradaException,
      entidadeNaoEncontradaException.getMessage(),
      new HttpHeaders(),
      HttpStatus.NOT_FOUND,
      webRequest
    );
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> tratarEntidadeEmUsoException(
    EntidadeEmUsoException entidadeEmUsoException,
    WebRequest webRequest) {

    return handleExceptionInternal(
      entidadeEmUsoException,
      entidadeEmUsoException.getMessage(),
      new HttpHeaders(),
      HttpStatus.CONFLICT,
      webRequest);
  }
}