package com.rafalzwiazek.todo.controller;
import com.rafalzwiazek.todo.data.Priority;
import com.rafalzwiazek.todo.data.Task;
import com.rafalzwiazek.todo.data.User;
import com.rafalzwiazek.todo.service.TaskRepository;
import com.rafalzwiazek.todo.service.UserRepository;
import com.rafalzwiazek.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ApplicationController {
    @Autowired
    TaskRepository taskService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping("/**")
    @ResponseBody
    public String test(){
        Task task = Task.taskInit("Test", Priority.HIGH);
        taskService.save(task);
        return "Saved Succesfully";
    }
    @GetMapping("/test")
    @ResponseBody
    public String testowo(){
        User user = User.userInit("Test", "tadeusz");
        return userService.saveUnique(user);
    }

}
