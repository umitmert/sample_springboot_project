package com.iyzico.service;

import java.util.List;



import com.iyzico.domain.Todo;
import com.iyzico.domain.User;


public interface TodoService{

	public Todo save(Todo todo);

	public Todo update(Todo todo);

	public void delete(Integer id);

	public List<Todo> findAll();

	public Todo findById(Integer id);

	public void flush();

	public List<Todo> findAllUserTodos(User user);


 
 

}