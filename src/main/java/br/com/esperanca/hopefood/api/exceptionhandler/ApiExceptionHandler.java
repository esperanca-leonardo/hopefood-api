package br.com.esperanca.hopefood.api.exceptionhandler;

import br.com.esperanca.hopefood.api.exceptionhandler.enuns.TipoErro;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.esperanca.hopefood.domain.exceptions.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    Throwable causaRaiz = ExceptionUtils.getRootCause(ex);

    if (causaRaiz instanceof InvalidFormatException) {
     return handleInvalidFormatException((InvalidFormatException) causaRaiz,
       headers, status, request
     );
    }
    TipoErro tipoErro = TipoErro.PROBLEMA_DE_SINTAXE;

    final String MENSAGEM = "Corpo da requisição está inválido. " +
      "Verifique erro de sintaxe.";
    Erro erro = criarErroBuilder(tipoErro, status, MENSAGEM).build();

    return handleExceptionInternal(ex, erro, headers, status, request);
  }

  private String campoInvalido(InvalidFormatException invalidFormatException) {
    return invalidFormatException
      .getPath()
      .stream()
      .map(Reference::getFieldName)
      .collect(Collectors.joining("."));
  }

  @ExceptionHandler(InvalidFormatException.class)
  private ResponseEntity<Object> handleInvalidFormatException(
      InvalidFormatException invalidFormatException, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    TipoErro tipoErro = TipoErro.PROBLEMA_DE_SINTAXE;

    String campoInvalido = campoInvalido(invalidFormatException);
    String mensagem = "A propriedade '%s' recebeu o valor '%s', que é do tipo " +
      "inválido. Por favor, corrija para o tipo '%s' e tente novamente";

    mensagem = String.format(mensagem, campoInvalido,
      invalidFormatException.getValue(),
      invalidFormatException.getTargetType().getSimpleName()
    );
    Erro erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(invalidFormatException, erro, headers,
      status, request
    );
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
      Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

    if (body == null) {
      body = Erro.builder()
        .status(status.value())
        .titulo(status.getReasonPhrase())
        .build();
    }
    else if (body instanceof String) {
      body = Erro.builder()
        .status(status.value())
        .titulo((String) body)
        .build();
    }
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  private Erro.ErroBuilder criarErroBuilder(TipoErro tipoErro,
      HttpStatus httpStatus, String mensagem) {

    return Erro.builder()
      .status(httpStatus.value())
      .tipo(tipoErro.getUri())
      .titulo(tipoErro.getTitulo())
      .detalhe(mensagem);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(
      NegocioException negocioException, WebRequest webRequest) {

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    TipoErro tipoErro = TipoErro.ERRO_NEGOCIO;

    String mensagem = negocioException.getMessage();
    Erro erro = criarErroBuilder(tipoErro, httpStatus, mensagem).build();

    return handleExceptionInternal(negocioException, erro, new HttpHeaders(),
      httpStatus, webRequest
    );
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontradaException(
      EntidadeNaoEncontradaException entidadeNaoEncontradaException,
      WebRequest webRequest) {

    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    TipoErro tipoErro = TipoErro.ENTIDADE_NAO_ENCONTRADA;

    String mensagem = entidadeNaoEncontradaException.getMessage();
    Erro erro = criarErroBuilder(tipoErro, httpStatus, mensagem).build();

    return handleExceptionInternal(entidadeNaoEncontradaException, erro,
      new HttpHeaders(), httpStatus, webRequest
    );
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> handleEntidadeEmUsoException(
      EntidadeEmUsoException entidadeEmUsoException, WebRequest webRequest) {

    HttpStatus httpStatus = HttpStatus.CONFLICT;
    TipoErro tipoErro = TipoErro.ENTIDADE_EM_USO;

    String mensagem = entidadeEmUsoException.getMessage();
    Erro erro = criarErroBuilder(tipoErro, httpStatus, mensagem).build();

    return handleExceptionInternal(entidadeEmUsoException, erro,
      new HttpHeaders(), httpStatus, webRequest
    );
  }
}