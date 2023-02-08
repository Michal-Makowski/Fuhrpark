package com.makowski.fuhrpark.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.makowski.fuhrpark.entity.User;
import com.makowski.fuhrpark.exception.EntityNotFoundException;
import com.makowski.fuhrpark.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    public User getUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user,username);
    }
    public User saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); 
        return userRepository.save(user);
    }

    static User unwrapUser(Optional<User> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }

    static User unwrapUser(Optional<User> entity, String username){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(username, User.class);
    }
}
