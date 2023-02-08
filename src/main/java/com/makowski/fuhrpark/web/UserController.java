package com.makowski.fuhrpark.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import com.makowski.fuhrpark.entity.User;
import com.makowski.fuhrpark.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUser(id).getUsername(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
