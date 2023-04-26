package com.controllers;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users",userService.fetchUserList());
        return "index";
    }

    @GetMapping("/create_user")
    public String create_user(Model model){
        model.addAttribute("user", new User());
        return "create_user";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        System.out.println(user);
        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String showUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userRepository.getReferenceById(id));
        return "show";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userRepository.getReferenceById(id));
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute(value="user") User user){
        //User us = userRepository.getReferenceById(id);
        userService.updateUser(user, id);
        return "redirect:/show/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
