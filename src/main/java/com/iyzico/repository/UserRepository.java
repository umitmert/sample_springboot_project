package com.iyzico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.iyzico.domain.Role;
import com.iyzico.domain.User;


@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

	@Query("SELECT p FROM User p WHERE LOWER(p.username) = LOWER(:username)")
    public User findByUsername(@Param("username") String username);
	
	@Query(value = "SELECT d.role FROM User d  WHERE  d.id  = :id")
	public Role getRoleId(@Param("id") Integer id); 
}
