package com.esperanca.hopefood.domain.city.services.findbyid;

import com.esperanca.hopefood.domain.city.entities.City;
import com.esperanca.hopefood.domain.city.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityFindByIdServiceImpl implements CityFindByIdService {

	@Autowired
	private CityRepository repository;

	@Override
	public City findById(Long id) {
		return repository.findById(id)
				.orElseGet(City::new);
	}
}
