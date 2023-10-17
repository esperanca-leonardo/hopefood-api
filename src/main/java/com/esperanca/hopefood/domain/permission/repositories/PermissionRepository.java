package com.esperanca.hopefood.domain.permission.repositories;

import com.esperanca.hopefood.domain.permission.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
