package com.iyzico.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
        return "editTodo";
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
    	User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		todo.setUser(activeUser);
		System.out.println(todo.getId());
        todoService.save(todo);
        todoService.flush();
        return "redirect:/todo/new";
    }
    
    @RequestMapping(value = "editTodo", method = RequestMethod.POST)
    public String editTodo(@Valid Todo todo,Model model, BindingResult bindingResult){
    	User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		todo.setUser(activeUser);
        todoService.update(todo);
        
        model.addAttribute("todo", new Todo());
        model.addAttribute("userTodos", todoService.findAllUserTodos(activeUser));
        return "userTodos";
    }

    @RequestMapping("todo/delete/{id}")
    public String delete(@PathVariable Integer id,Model model){
        todoService.delete(id);
        User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("todo", new Todo());
        model.addAttribute("userTodos", todoService.findAllUserTodos(activeUser));
        return "userTodos";
    }

}
