package com.ibss.service;

import java.util.List;

import com.ibss.domain.Todo;
import com.ibss.domain.User;


public interface TodoService{

	public Todo save(Todo todo);

	public Todo update(Todo todo);

	public void delete(Integer id);

	public List<Todo> findAll();

	public Todo findById(Integer id);

	public void flush();

	public List<Todo> findAllUserTodos(User user);


 
 

}