package com.esperanca.hopefood.domain.permission.entities;

import com.esperanca.hopefood.core.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Permission extends AbstractEntity {

	private String name;
	private String description;
}
