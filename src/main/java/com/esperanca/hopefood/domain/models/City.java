package com.esperanca.hopefood.domain.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(nullable = false)
	private State state;

	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private OffsetDateTime createdAt;

	@Column(nullable = false, columnDefinition = "datetime")
	@UpdateTimestamp
	private OffsetDateTime updatedAt;
}
