package br.com.esperanca.hopefood.infrastructure.repositories;

import br.com.esperanca.hopefood.domain.repositories.custom.JpaRepositoryCustom;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class JpaRepositoryCustomImpl<T, ID> extends SimpleJpaRepository<T, ID>
    implements JpaRepositoryCustom<T, ID> {

  private EntityManager entityManager;

  public JpaRepositoryCustomImpl(JpaEntityInformation<T, ?> entityInformation,
      EntityManager entityManager) {

    super(entityInformation, entityManager);

    this.entityManager = entityManager;
  }

  @Override
  public Optional<T> buscarPrimeiraOcorrencia() {
    String jpql = "FROM " + getDomainClass().getName();

    T entity = entityManager
      .createQuery(jpql, getDomainClass())
      .setMaxResults(1)
      .getSingleResult();

    return Optional.ofNullable(entity);
  }
}
