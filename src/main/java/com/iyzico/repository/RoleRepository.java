package com.iyzico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.iyzico.domain.Role;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role>{

	@Query("SELECT p FROM Role p WHERE LOWER(p.rolename) = LOWER(:rolename)")
    public Role getRoleName(@Param("rolename") String username);
}
