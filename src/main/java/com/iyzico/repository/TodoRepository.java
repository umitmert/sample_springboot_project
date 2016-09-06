package com.iyzico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.iyzico.domain.Todo;
import com.iyzico.domain.User;

@Transactional(readOnly = true)
public interface TodoRepository extends JpaRepository<Todo, Integer>, JpaSpecificationExecutor<Todo>{
	
	List<Todo> findAllByUser(User user);
	
	@Query("SELECT t FROM Todo t WHERE LOWER(t.desc) = LOWER(:desc)")
    public Todo findByDesc(@Param("desc") String dec);
}
