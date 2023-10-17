package com.esperanca.hopefood.domain.city.services.findbyid;

import com.esperanca.hopefood.core.interfaces.operations.FindById;
import com.esperanca.hopefood.domain.city.entities.City;

public interface CityFindByIdService extends FindById<Long, City> {

	City findById(Long id);
}
