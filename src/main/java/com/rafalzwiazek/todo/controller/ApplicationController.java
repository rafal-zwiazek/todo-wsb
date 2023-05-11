package com.rafalzwiazek.todo.controller;
import com.rafalzwiazek.todo.data.User;
import com.rafalzwiazek.todo.errors.ErrorUser;
import com.rafalzwiazek.todo.repositories.TaskRepository;
import com.rafalzwiazek.todo.repositories.UserRepository;
import com.rafalzwiazek.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("user")

public class ApplicationController {
    @Autowired
    TaskRepository taskService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
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
    @RequestMapping("/validate-register")
    public String registerValidation(Model model, @ModelAttribute("user") User user){
        if(userService.saveUnique(user).equals(ErrorUser.USER_SAVED)) {
            return "approved";
        }
        return "userExists";
    }
    @RequestMapping("/login")
    public String showLoginPage(Model model){
        return "loginPage";
    }
    @RequestMapping("/login-validate")
    public String loginValidation(Model model, @ModelAttribute("user") User user){
        if(userService.loginUser(user).equals(ErrorUser.LOGIN_SUCCESSFUL)){
            return "logged";
        }
    return "loginFailed";
    }
}
