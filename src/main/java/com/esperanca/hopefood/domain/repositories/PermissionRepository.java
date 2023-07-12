package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
