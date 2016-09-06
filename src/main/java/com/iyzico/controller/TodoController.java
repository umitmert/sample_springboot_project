package com.iyzico.controller;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iyzico.domain.Todo;
import com.iyzico.domain.User;
import com.iyzico.service.TodoService;

@Controller
public class TodoController {

    private TodoService todoService;

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    
    @RequestMapping("todo/{id}")
    public String showTodo(@PathVariable Integer id, Model model){
        model.addAttribute("todo", todoService.findById(id));
        return "todoshow";
    }

    @RequestMapping("todo/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("todo", todoService.findById(id));
        return "todoform";
    }

    @RequestMapping("todo/new")
    public String newTodo(Model model){
        model.addAttribute("todo", new Todo());
        User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userTodos", todoService.findAllUserTodos(activeUser));
        return "userTodos";
    }
    
    @RequestMapping("todo/list")
    public String todoList(Model model){
        User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userTodos", todoService.findAllUserTodos(activeUser));
        return "userTodos";
    }

    @RequestMapping(value = "todo", method = RequestMethod.POST)
    public String saveTodo(@Valid Todo todo,BindingResult bindingResult){
    	if(bindingResult.hasErrors()) return "newTodo";
    	User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		todo.setUser(activeUser);
        todoService.save(todo);
        return "redirect:/todo/new";
    }

    @RequestMapping("todo/delete/{id}")
    public String delete(@PathVariable Integer id){
        todoService.delete(id);
        return "redirect:/todos";
    }

}
