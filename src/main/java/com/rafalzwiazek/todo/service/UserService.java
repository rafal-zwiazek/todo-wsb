package com.rafalzwiazek.todo.service;

import com.rafalzwiazek.todo.data.User;
import com.rafalzwiazek.todo.errors.ErrorUser;
import com.rafalzwiazek.todo.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public ErrorUser saveUnique(User user) throws DataIntegrityViolationException {
        try {
            userRepository.save(user);

            return ErrorUser.USER_SAVED;
        } catch(Exception e){
            return ErrorUser.USER_EXISTS;
        }
    }
}
