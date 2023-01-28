package br.com.esperanca.hopefood.domain.repositories.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface JpaRepositoryCustom<T, ID> extends JpaRepository<T, ID> {

  Optional<T> buscarPrimeiraOcorrencia();
}
