package com.James.demo.controller;

import com.James.demo.entity.User;
import com.James.demo.service.UserService;
import com.James.demo.utils.ControllerValidation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User Api")
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getUserList() {
        Iterable<User> userList = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @PostMapping(value = "/create")
    public ResponseEntity addUser(@RequestBody User user) {
        return ControllerValidation.userVerify(user, userService);
    }

    @PostMapping(value = "/update")
    public ResponseEntity updateUser(@RequestBody User user) {
        User result;

        try {
            result = userService.findById(user.getId());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not exist!!!");
        }


        return ControllerValidation.userVerify(user, userService);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        User result;

        try {
            result = userService.findById(id);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not exist!!!");
        }

        userService.delete(result.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

}
