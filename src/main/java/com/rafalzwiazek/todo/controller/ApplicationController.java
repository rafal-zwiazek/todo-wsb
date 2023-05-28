package com.rafalzwiazek.todo.controller;
import com.rafalzwiazek.todo.data.Task;
import com.rafalzwiazek.todo.data.User;
import com.rafalzwiazek.todo.errors.ErrorUser;
import com.rafalzwiazek.todo.repositories.TaskRepository;
import com.rafalzwiazek.todo.repositories.UserRepository;
import com.rafalzwiazek.todo.service.TaskService;
import com.rafalzwiazek.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@SessionAttributes("user")

public class ApplicationController {
    User user;
    @Autowired
    TaskService taskService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    TaskRepository taskRepository;
    @GetMapping("/")
    public String indexMain(){
    return "menu";
    }
    @GetMapping("/author")
    public String author(){
return "about";
    }
    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register")
    public String registerValidation(Model model, @ModelAttribute("user") User user){
        switch(userService.saveUnique(user)) {
            case USER_SAVED -> {
                this.user = user;
                return "registerSuccess";
            }
            case PASSWORD_CANNOT_BE_BLANK, USER_EXISTS, USERNAME_CANNOT_BE_BLANK -> {
                return "registerFail";
            }
        }
        return "registerFail";
    }
    @GetMapping("/login")
    public String showLoginPage(Model model){
        if(this.user != null){
            return "alreadyLogged";
        }
        User user = new User();
        model.addAttribute("user", user);
        return "loginPage";
    }
    @PostMapping("/user")
    public String userPage(Model model, @ModelAttribute("user") User user){
        if(userService.loginUser(user).equals(ErrorUser.LOGIN_SUCCESSFUL)){
            this.user=user;
            List<Task> taskList = taskRepository.findByUser_id(this.user.getId());
            model.addAttribute("tasksList", taskList);
            return "userPage";
        }
        this.user = null;
    return "loginFailed";
    }
    @GetMapping("/user")
    public String showUserPage(Model model){
            List<Task> taskList = taskRepository.findByUser_id(this.user.getId());
            model.addAttribute("tasksList", taskList);
            return "userPage";
    }
    @GetMapping("/user/add")
    public String addTask(Model model){
        Task task = new Task();
        model.addAttribute("task",task);
        return "addTask";
    }
    @PostMapping("/user/add")
    public String addTask(Model model, @ModelAttribute("task") Task task){
        task.setUser(this.user);
        taskService.saveTaskWithTimeStamp(task);
        return "addTaskSuccess";
    }
    @GetMapping("/user/delete")
    public String delete(Model model){
        List<Task> taskList = taskRepository.findByUser_id(this.user.getId());
        model.addAttribute("tasksList", taskList);
        Task task = new Task();
        model.addAttribute("task", task);
        return "deleteTask";
    }
    @PostMapping("/user/delete")
    public String delete(Model model, @ModelAttribute("task") Task task){
        taskService.deleteTaskById(task, this.user);
        List<Task> taskList = taskRepository.findByUser_id(this.user.getId());
        model.addAttribute("tasksList", taskList);
        model.addAttribute("task", task);
        return "deleteTaskSuccess";
    }
    @GetMapping("/logout")
    public String logout(Model model){
        this.user = null;
        return "logout";
    }

}
