package com.rafalzwiazek.todo.controller;
import com.rafalzwiazek.todo.data.Priority;
import com.rafalzwiazek.todo.data.Task;
import com.rafalzwiazek.todo.data.User;
import com.rafalzwiazek.todo.errors.ErrorUser;
import com.rafalzwiazek.todo.service.TaskRepository;
import com.rafalzwiazek.todo.service.UserRepository;
import com.rafalzwiazek.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ApplicationController {
    @Autowired
    TaskRepository taskService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping("/")
    public ModelAndView indexMain(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu");
        return modelAndView;
    }
    @GetMapping("/author")
    public ModelAndView author(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @GetMapping("/test")
    @ResponseBody
    public String testowo(){
        User user = User.userInit("Test", "tadeusz");
        switch(userService.saveUnique(user)){
            case USER_SAVED -> {
                return "User zarejestrowany";
            }
            case USER_EXISTS -> {
                return "User istnieje w bazie";
            }

        }
        return "chuj";
    }
}
