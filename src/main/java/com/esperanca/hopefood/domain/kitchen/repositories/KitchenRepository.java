package com.esperanca.hopefood.domain.kitchen.repositories;

import com.esperanca.hopefood.domain.kitchen.entities.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

}
