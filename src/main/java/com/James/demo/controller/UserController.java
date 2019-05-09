package com.James.demo.controller;

import com.James.demo.entity.User;
import com.James.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUserList(Model m) {
        m.addAttribute("userList", userService.findAll());
        return "userList";
    }

    @GetMapping(value = "/create")
    public String getUserForm(Model m) {
        m.addAttribute("user", new User());
        m.addAttribute("action", "create");
        return "userForm";
    }

    @PostMapping(value = "/create")
    public String addUser(Model m, @ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            m.addAttribute("action", "create");
            return "userForm";
        }

        userService.insertByUser(user);
        return "redirect:/users/";
    }

    @GetMapping(value = "/update/{id}")
    public String updateUserForm(Model m, @PathVariable Long id) {
        m.addAttribute("user", userService.findById(id));
        m.addAttribute("action", "update");
        return "userForm";
    }

    @PostMapping(value = "/update")
    public String updateUser(Model m, @ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            m.addAttribute("action", "update");
            return "userForm";
        }

        userService.insertByUser(user);
        return "redirect:/users/";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users/";
    }

}
