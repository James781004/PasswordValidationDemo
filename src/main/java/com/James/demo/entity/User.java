package com.James.demo.entity;

import com.James.demo.utils.Password;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8148993073056325684L;
    
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "no empty allowed")
    @Size(min = 5, max = 12, message = "Must be between 5 and 12 characters in length")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z]).{5,12}$", message = "Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each")
    private String name;

    @Password
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
