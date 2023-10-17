package com.esperanca.hopefood.domain.restaurant.repositories;

import com.esperanca.hopefood.domain.restaurant.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
