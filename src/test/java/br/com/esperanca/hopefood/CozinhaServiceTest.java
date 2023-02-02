package br.com.esperanca.hopefood;

import br.com.esperanca.hopefood.domain.entities.Cozinha;
import br.com.esperanca.hopefood.domain.exceptions.cozinhas.CozinhaEmUsoException;
import br.com.esperanca.hopefood.domain.exceptions.cozinhas.CozinhaNaoEncontradaException;
import br.com.esperanca.hopefood.domain.services.CozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CozinhaServiceTest {

  @Autowired
  private CozinhaService cozinhaService;

  @Test
  public void deveRetornarQuandoInformadoUmIdValido() {
    assertNotNull(
      cozinhaService.buscarPorId(1L)
    );
  }

  @Test
  public void naoDeveRetornarQuandoInformadoUmIdInvalido() {
    assertThrows(CozinhaNaoEncontradaException.class,
      () -> cozinhaService.buscarPorId(100L)
    );
  }

  @Test
  public void deveSalvarQuandoInformadoUmNomeValido() {
    var cozinha = new Cozinha("Test");
    var cozinhaSalva = cozinhaService.salvar(cozinha);

    assertNotNull(cozinhaSalva);
  }

  @Test
  public void naoDeveSalvarQuandoNomeForNulo() {
    var cozinha = new Cozinha(null);

    assertThrows(ConstraintViolationException.class,
      () -> cozinhaService.salvar(cozinha)
    );
  }

  @Test
  public void naoDeveSalvarQuandoNomeForStringVazia() {
    var cozinha = new Cozinha("");

    assertThrows(ConstraintViolationException.class,
      () -> cozinhaService.salvar(cozinha)
    );
  }

  @Test
  public void naoDeveSalvarQuandoNomeTiverSomenteEspacosEmBranco() {
    var cozinha = new Cozinha(" ");

    assertThrows(ConstraintViolationException.class,
      () -> cozinhaService.salvar(cozinha)
    );
  }

  @Test
  public void deveExcluirQuandoInformadoUmIdValido() {
    cozinhaService.remover(5L);
  }

  @Test
  public void naoDeveExcluirQuandoAMesmaEstiverSendoUsadaPorOutraTabela() {
    assertThrows(CozinhaEmUsoException.class,
      () -> cozinhaService.remover(3L)
    );
  }

  @Test
  public void naoDeveExcluirQuandoAMesmaNaoForEncontrada() {
    assertThrows(CozinhaNaoEncontradaException.class,
      () -> cozinhaService.remover(100L)
    );
  }
}
