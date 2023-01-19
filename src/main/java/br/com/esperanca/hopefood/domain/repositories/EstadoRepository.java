package br.com.esperanca.hopefood.domain.repositories;

import br.com.esperanca.hopefood.domain.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

  List<Estado> findByNome(String nome);

  List<Estado> findByNomeContaining(String nome);
}
