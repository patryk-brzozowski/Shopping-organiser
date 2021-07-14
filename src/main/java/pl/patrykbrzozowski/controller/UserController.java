package pl.patrykbrzozowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.patrykbrzozowski.exceptions.RegisterFailedException;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.model.dto.RegisterDto;
import pl.patrykbrzozowski.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String createUserForm() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@Valid RegisterDto dto, BindingResult result) {
        User user=null;

        if(!result.hasErrors() ){
            try {
                user=userService.registerUser(dto);
            }catch (RegisterFailedException e){
                return "register";
            }

            if(user!=null) {
                return "redirect:/";
            }
        }
        return "register";

    }
}