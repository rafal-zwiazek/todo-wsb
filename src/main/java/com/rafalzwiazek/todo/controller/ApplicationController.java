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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView author(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }
    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register")
    public String registerPOST(@ModelAttribute("user") User user){
return "approved";
    }
    @RequestMapping("/approved")
    public String approved(Model model, @ModelAttribute("user") User user){
        if(userService.saveUnique(user).equals(ErrorUser.USER_SAVED)) {
            return "approved";
        }
        return "menu";
    }
}
