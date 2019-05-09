package com.James.demo.utils;

import com.James.demo.entity.User;
import com.James.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.James.demo.utils.ValidationUtil.*;

public class ControllerValidation {

    public static ResponseEntity userVerify(User user, UserService userService) {
        String password = user.getPassword();
        if (!checkLength(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Must be between 5 and 12 characters in length.");
        }

        if (!checkCharacter(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Must consist of a mixture of lowercase letters and numerical digits only,\n" +
                    "                    with at least one of each.");
        }

        if (!checkSequence(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Must not contain any sequence of characters immediately followed by the same\n" +
                    "                    sequence.");

        }

        System.out.println("Valid password");
        User result = userService.insertByUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
