package com.esperanca.hopefood.domain.state.repositories;

import com.esperanca.hopefood.domain.state.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
