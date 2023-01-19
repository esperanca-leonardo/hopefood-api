package br.com.esperanca.hopefood.infrastructure.repositories.specs;

import br.com.esperanca.hopefood.domain.entities.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteBuilderSpec {

  public static Specification<Restaurante> comFreteGratis() {
    return ((root, query, criteriaBuilder) ->
      criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO)
    );
  }
}
