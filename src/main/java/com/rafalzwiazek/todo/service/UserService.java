package com.rafalzwiazek.todo.service;

import com.rafalzwiazek.todo.data.User;
import com.rafalzwiazek.todo.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String saveUnique(User user) throws DataIntegrityViolationException {
        try {
            userRepository.save(user);

            return "User saved successfully";
        } catch(Exception e){
            return "User exists";
        }
    }
}
