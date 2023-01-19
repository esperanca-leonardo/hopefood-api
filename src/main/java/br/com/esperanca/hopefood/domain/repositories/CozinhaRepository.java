package br.com.esperanca.hopefood.domain.repositories;

import br.com.esperanca.hopefood.domain.entities.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

  List<Cozinha> findByNome(String nome);

  List<Cozinha> findByNomeContaining(String nome);
}
