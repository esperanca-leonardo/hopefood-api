package br.com.esperanca.hopefood.domain.repositories;

import br.com.esperanca.hopefood.domain.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> { }
