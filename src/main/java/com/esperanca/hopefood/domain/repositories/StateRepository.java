package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
