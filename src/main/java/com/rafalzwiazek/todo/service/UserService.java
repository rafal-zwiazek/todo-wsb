package com.rafalzwiazek.todo.service;

import com.rafalzwiazek.todo.data.User;
import com.rafalzwiazek.todo.errors.ErrorUser;
import com.rafalzwiazek.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public ErrorUser loginUser(User user){
        List<User> validateUser = userRepository.findByUsername(user.getUsername());
        if(validateUser.isEmpty()){
            return ErrorUser.USER_DOESNT_EXISTS;
        } else
            if(user.getPassword().equals(validateUser.get(0).getPassword()))
        {
                return ErrorUser.LOGIN_SUCCESSFUL;
        } else
        return ErrorUser.WRONG_PASSWORD;
    }
}
