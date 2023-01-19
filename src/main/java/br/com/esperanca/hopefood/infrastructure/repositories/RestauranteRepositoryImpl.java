package br.com.esperanca.hopefood.infrastructure.repositories;

import br.com.esperanca.hopefood.domain.entities.Restaurante;
import br.com.esperanca.hopefood.domain.repositories.RestauranteRepository;
import br.com.esperanca.hopefood.domain.repositories.custom.RestauranteRepositoryCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static br.com.esperanca.hopefood.infrastructure.repositories.specs.RestauranteBuilderSpec.comFreteGratis;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Lazy
  @Autowired
  private RestauranteRepository restauranteRepository;

  @Override
  public List<Restaurante> consultarPorTaxaFrete(BigDecimal taxaInicial,
                                                 BigDecimal taxaFinal) {
    var parametros = new HashMap<String, BigDecimal>();
    var jpql = new StringBuilder("FROM RestauranteEntity WHERE 1 = 1 ");

    if (taxaInicial != null) {
      jpql.append("AND taxaFrete >= :taxaInicial ");
      parametros.put("taxaInicial", taxaInicial);
    }
    if (taxaFinal != null) {
      jpql.append("AND taxaFrete <= :taxaFinal");
      parametros.put("taxaFinal", taxaFinal);
    }
    TypedQuery<Restaurante> query = entityManager
      .createQuery(jpql.toString(), Restaurante.class);

    parametros.forEach(query::setParameter);

    return query.getResultList();
  }

  @Override
  public List<Restaurante> consultarPorCozinha(Long cozinhaId) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder
      .createQuery(Restaurante.class);

    Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

    Predicate cozinhaPredicate = criteriaBuilder.equal(root.get("cozinhaEntity"), cozinhaId);

    criteriaQuery.where(cozinhaPredicate);

    return entityManager
      .createQuery(criteriaQuery)
      .getResultList();
  }

  @Override
  public List<Restaurante> consultarPorFreteGratis() {
    return restauranteRepository.findAll(comFreteGratis());
  }
}
