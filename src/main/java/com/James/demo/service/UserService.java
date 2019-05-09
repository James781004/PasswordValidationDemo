package com.James.demo.service;

import com.James.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User insertByUser(User user);

    User update(User user);

    User delete(Long id);
}
