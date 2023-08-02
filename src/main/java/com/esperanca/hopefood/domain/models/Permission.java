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
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@JoinColumn(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private OffsetDateTime createdAt;

	@JoinColumn(nullable = false, columnDefinition = "datetime")
	@UpdateTimestamp
	private OffsetDateTime updatedAt;
}
