package com.ibss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ibss.domain.Role;
import com.ibss.domain.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

	@Query("SELECT p FROM User p WHERE LOWER(p.username) = LOWER(:username)")
    public User findByUsername(@Param("username") String username);
	
	@Query(value = "SELECT d.role FROM User d  WHERE  d.id  = :id")
	public Role getRoleId(@Param("id") Integer id); 
}
