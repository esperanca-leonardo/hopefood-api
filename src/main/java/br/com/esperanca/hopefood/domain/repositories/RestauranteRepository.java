package br.com.esperanca.hopefood.domain.repositories;

import br.com.esperanca.hopefood.domain.entities.Restaurante;
import br.com.esperanca.hopefood.domain.repositories.custom.JpaRepositoryCustom;
import br.com.esperanca.hopefood.domain.repositories.custom.RestauranteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends
    JpaRepositoryCustom<Restaurante, Long>,
    JpaSpecificationExecutor<Restaurante>, RestauranteRepositoryCustom {

  List<Restaurante> consultarPorNome(String nome);
}
