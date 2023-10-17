package com.esperanca.hopefood.domain.state.entities;

import com.esperanca.hopefood.core.entities.AbstractEntity;
import com.esperanca.hopefood.domain.city.entities.City;
import com.esperanca.hopefood.domain.restaurant.entities.Restaurant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class State extends AbstractEntity {

	private String name;

	@OneToMany(mappedBy = "state")
	private List<City> cities;
}
