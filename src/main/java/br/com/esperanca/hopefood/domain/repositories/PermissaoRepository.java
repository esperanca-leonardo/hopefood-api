package br.com.esperanca.hopefood.domain.repositories;

import br.com.esperanca.hopefood.domain.entities.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> { }
