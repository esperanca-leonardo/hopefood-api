package com.esperanca.hopefood.domain.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToMany
	private List<Permission> permissions;

	@JoinColumn(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private OffsetDateTime createdAt;

	@JoinColumn(nullable = false, columnDefinition = "datetime")
	@UpdateTimestamp
	private OffsetDateTime updatedAt;
}
