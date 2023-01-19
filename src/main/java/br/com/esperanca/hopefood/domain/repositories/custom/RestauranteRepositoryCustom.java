package br.com.esperanca.hopefood.domain.repositories.custom;

import br.com.esperanca.hopefood.domain.entities.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryCustom {
  List<Restaurante> consultarPorTaxaFrete(BigDecimal taxaInicial,
                                          BigDecimal taxaFinal);

  List<Restaurante> consultarPorCozinha(Long cozinhaId);

  List<Restaurante> consultarPorFreteGratis();
}
