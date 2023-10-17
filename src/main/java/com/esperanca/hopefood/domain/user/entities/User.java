package com.esperanca.hopefood.domain.user.entities;

import com.esperanca.hopefood.domain.group.entities.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@ManyToMany
	private List<Group> groups;

	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private OffsetDateTime createdAt;

	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private OffsetDateTime updatedAt;
}
