package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
