package com.esperanca.hopefood.domain.city.entities;

import com.esperanca.hopefood.core.entities.AbstractEntity;
import com.esperanca.hopefood.domain.state.entities.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class City extends AbstractEntity {

	private String name;

	@ManyToOne
	private State state;

	public void updateState(State state) {
		this.state = state;
	}
}
