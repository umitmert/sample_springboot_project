package com.iyzico.service;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibss.domain.Todo;
import com.ibss.domain.TodoState;
import com.ibss.repository.TodoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TodoServiceTest {


    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void create() {
    	String desc = "Todo 1";
    	Todo todoInfo = new Todo();
    	todoInfo.setDesc(desc);
    	todoInfo.setState(TodoState.WAITING);
    	
    	testEntityManager.persist(todoInfo);

        Todo todo = this.todoRepository.findByDesc(desc);
        assertThat(todo.getState().name(), equalTo(TodoState.WAITING.name()));
    }
}
