package br.com.esperanca.hopefood.api.exceptionhandler;

import br.com.esperanca.hopefood.api.exceptionhandler.enuns.TipoErro;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.esperanca.hopefood.domain.exceptions.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String MENSAGEM_GENERICA = "Ocorreu um erro interno " +
    "no sistema. Tente novamente e se o problema persistir, entre em " +
    "contato conosco";

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception exception,
      Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

    if (body == null) {
      body = Erro.builder()
        .status(status.value())
        .titulo(status.getReasonPhrase())
        .dataHora(LocalDateTime.now())
        .descricao(MENSAGEM_GENERICA)
        .build();
    }
    else if (body instanceof String) {
      body = Erro.builder()
        .status(status.value())
        .titulo((String) body)
        .dataHora(LocalDateTime.now())
        .descricao(MENSAGEM_GENERICA)
        .build();
    }
    return super.handleExceptionInternal(exception, body, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException exception, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    Throwable causaRaiz = ExceptionUtils.getRootCause(exception);

    if (causaRaiz instanceof InvalidFormatException) {
     return tratarInvalidFormat((InvalidFormatException) causaRaiz, headers,
       status, request
     );
    }
    else if (causaRaiz instanceof IgnoredPropertyException) {
      return tratarIgnoredProperty((IgnoredPropertyException) causaRaiz,
        headers, status, request
      );
    }
    else if (causaRaiz instanceof UnrecognizedPropertyException) {
      return tratarUnrecognizedProperty(
        (UnrecognizedPropertyException) causaRaiz, headers, status, request
      );
    }
    var mensagem = "Corpo da requisição está inválido. " +
      "Verifique erro de sintaxe.";

    var tipoErro = TipoErro.PROBLEMA_DE_SINTAXE;
    var erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(exception, erro, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      NoHandlerFoundException exception, HttpHeaders headers, HttpStatus status,
      WebRequest request) {

    var tipoErro = TipoErro.RECURSO_NAO_ENCONTRADO;
    var mensagem = "O recurso '%s' que você tentou acessar, é inexistente";

    mensagem = String.format(mensagem, exception.getRequestURL());
    var erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(exception, erro, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    var tipoErro = TipoErro.DADOS_INVALIDOS;

    var mensagem = "Um ou mais campos estão inválidos. Faça o preenchimento " +
      "correto e tente novamente.";

    BindingResult result = exception.getBindingResult();

    List<Campo> campos = result.getFieldErrors()
      .stream()
      .map(fieldError -> Campo.builder()
          .nome(fieldError.getField())
          .mensagem(fieldError.getDefaultMessage())
        .build()
      ).collect(Collectors.toList());

    var erro = criarErroBuilder(tipoErro, status, mensagem)
      .campos(campos)
      .build();

    return handleExceptionInternal(exception, erro, headers, status, request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> tratarExcecoesNaoCapturadas(Exception exception,
      WebRequest request) {

    var status = HttpStatus.INTERNAL_SERVER_ERROR;
    var tipoErro = TipoErro.ERRO_DE_SISTEMA;

    var erro = criarErroBuilder(tipoErro, status, MENSAGEM_GENERICA).build();

    exception.printStackTrace();

    return handleExceptionInternal(exception, erro, new HttpHeaders(), status,
      request
    );
  }

  public ResponseEntity<Object> tratarMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException exception, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    var tipoErro = TipoErro.PARAMETRO_INVALIDO;

    var mensagem = "O parâmetro de URL '%s' recebeu o valor '%s', que é de " +
      "um tipo inválido. Corriga e informe um valor compatível com o tipo %s";

    String parametroUrl = exception.getName();
    Object valorEnviado = exception.getValue();

    String tipoCorreto = Objects.requireNonNull(
      exception.getRequiredType()
    ).getSimpleName();

    mensagem = String.format(mensagem, parametroUrl, valorEnviado, tipoCorreto);
    var erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(exception, erro, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      TypeMismatchException exception, HttpHeaders headers, HttpStatus status,
      WebRequest request) {

    if (exception instanceof MethodArgumentTypeMismatchException) {
      return tratarMethodArgumentTypeMismatch(
        (MethodArgumentTypeMismatchException) exception, headers, status,
        request
      );
    }
    return super.handleTypeMismatch(exception, headers, status, request);
  }

  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<Object> tratarInvalidFormat(
      InvalidFormatException exception, HttpHeaders headers, HttpStatus status,
      WebRequest request) {

    var tipoErro = TipoErro.TIPO_DE_DADO_INCORRETO;
    String campoInvalido = buscarCampoInvalido(exception.getPath());

    var mensagem = "A propriedade '%s' recebeu o valor '%s', que é do tipo " +
      "inválido. Por favor, corrija para o tipo '%s' e tente novamente";

    mensagem = String.format(mensagem, campoInvalido, exception.getValue(),
      exception.getTargetType().getSimpleName()
    );
    var erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(exception, erro, headers, status, request);
  }

  @ExceptionHandler(UnrecognizedPropertyException.class)
  public ResponseEntity<Object> tratarUnrecognizedProperty(
      UnrecognizedPropertyException exception, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    var tipoErro = TipoErro.PROPRIEDADE_NAO_ENCONTRADA;

    String campoInvalido = buscarCampoInvalido(exception.getPath());

    var mensagem = "A propriedade '%s' não existe. Remova essa propriedade " +
      "e tente novamente";

    mensagem = String.format(mensagem, campoInvalido);
    var erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(exception, erro, headers, status, request);
  }

  @ExceptionHandler(IgnoredPropertyException.class)
  public ResponseEntity<Object> tratarIgnoredProperty(
      IgnoredPropertyException exception, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    var tipoErro = TipoErro.PROPRIEDADE_IGNORADA;

    String campoInvalido = buscarCampoInvalido(exception.getPath());

    var mensagem = "A propriedade '%s' não esta mais sendo utilizada. " +
      "Remova essa propriedade e tente novamente.";

    mensagem = String.format(mensagem, campoInvalido);
    var erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(exception, erro, headers, status, request);
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> tratarEntidadeNaoEncontrada(
      EntidadeNaoEncontradaException exception, WebRequest request) {

    var status = HttpStatus.NOT_FOUND;
    var tipoErro = TipoErro.RECURSO_NAO_ENCONTRADO;

    String mensagem = exception.getMessage();
    var erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(exception, erro, new HttpHeaders(), status,
      request
    );
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> tratarEntidadeEmUso(
      EntidadeEmUsoException exception, WebRequest request) {

    var status = HttpStatus.CONFLICT;
    var tipoErro = TipoErro.RECURSO_EM_USO;

    String mensagem = exception.getMessage();
    var erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(exception, erro, new HttpHeaders(), status,
      request
    );
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> tratarNegocio(NegocioException exception,
      WebRequest request) {

    var status = HttpStatus.BAD_REQUEST;
    var tipoErro = TipoErro.ERRO_NEGOCIO;

    String mensagem = exception.getMessage();
    var erro = criarErroBuilder(tipoErro, status, mensagem).build();

    return handleExceptionInternal(exception, erro, new HttpHeaders(), status,
      request
    );
  }

  private Erro.ErroBuilder criarErroBuilder(TipoErro tipoErro,
      HttpStatus status, String mensagem) {

    return Erro.builder()
      .status(status.value())
      .tipo(tipoErro.getUri())
      .titulo(tipoErro.getTitulo())
      .dataHora(LocalDateTime.now())
      .descricao(mensagem);
  }

  private String buscarCampoInvalido(List<Reference> references) {
    return references.stream()
      .map(Reference::getFieldName)
      .collect(Collectors.joining("."));
  }
}