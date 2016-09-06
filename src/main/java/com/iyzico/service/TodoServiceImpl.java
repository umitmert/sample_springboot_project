package com.iyzico.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyzico.config.CustomAuthenticationProvider;
import com.iyzico.domain.Todo;
import com.iyzico.domain.User;
import com.iyzico.repository.TodoRepository;


@Service
@Transactional
public class TodoServiceImpl implements TodoService {
	static Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

	@Autowired
	private TodoRepository todoDAO;

	@Autowired(required=true)
	private CustomAuthenticationProvider authenticationProvider;


	@Override
	@Transactional
	public Todo save(Todo todo){
		return todoDAO.save(todo);
	}
 

	@Override
	public void delete(Integer id){
		todoDAO.delete(id);
	}

	@Override
	public List<Todo> findAll() {
		return todoDAO.findAll();
	}

	@Override
	@Transactional
	public Todo findById(Integer id){
		return todoDAO.findOne(id);
	}

	@Override
	public void flush() {
		todoDAO.flush();
	}

	@Override
	
	public Todo update(Todo todo){
		todoDAO.save(todo);
		return todo;
	}


	@Override
	public List<Todo> findAllUserTodos(User user) {
		return todoDAO.findAllByUser(user);
	}

}