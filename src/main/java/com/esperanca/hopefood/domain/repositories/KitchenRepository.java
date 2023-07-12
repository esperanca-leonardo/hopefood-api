package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

}
