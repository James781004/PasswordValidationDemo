package com.James.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.James.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
