package com.iyzico.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iyzico.domain.Role;
import com.iyzico.domain.User;
import com.iyzico.service.UserService;
import com.iyzico.util.Util;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("user/{id}")
    public String showUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "usershow";
    }

    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "userform";
    }

    @RequestMapping("user/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "newUser";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveUser(@Valid User user,BindingResult bindingResult){
    	if(bindingResult.hasErrors()) return "newUser";
    	List<User> users = new ArrayList<User>();
		Role role = new Role();
		role.setRolename("Admin");
		user.setRole(role);
    	user.setPassword(Util.encryptPassword(user.getUsername(),user.getPassword()));
    	users.add(user);
		role.setUsers(users);
        userService.save(user);
        return "redirect:/user/" + user.getId();
    }

    @RequestMapping("user/delete/{id}")
    public String delete(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/users";
    }

}
