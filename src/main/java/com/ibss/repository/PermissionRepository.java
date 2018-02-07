package com.ibss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibss.domain.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Integer>{

	@Query("SELECT p FROM Permission p WHERE LOWER(p.permissionname) = LOWER(:name)")
    public Permission findByName(@Param("name") String name);
}
