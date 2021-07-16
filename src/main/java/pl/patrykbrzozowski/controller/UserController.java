package pl.patrykbrzozowski.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.patrykbrzozowski.exceptions.ConfirmationFailedException;
import pl.patrykbrzozowski.exceptions.EmailAlreadyExistException;
import pl.patrykbrzozowski.exceptions.RegisterFailedException;
import pl.patrykbrzozowski.exceptions.UserAlreadyExistException;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.model.dto.RegisterDto;
import pl.patrykbrzozowski.security.CurrentUser;
import pl.patrykbrzozowski.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String createUserForm(Model model) {
        model.addAttribute("dto", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("dto") @Valid RegisterDto dto, BindingResult result, Model model) {
        User user=null;


        if(!dto.getPassword().equals(dto.getConfirm_password())) {
            model.addAttribute("match", "Password doesn't match.");
        }


        try {
            userService.userExist(dto.getUserName());
        } catch (UserAlreadyExistException e) {
                model.addAttribute("userexists", "User with that user name already exists.");
                return "register";

        }

        try {
            userService.emailExist(dto.getEmail());
        } catch (EmailAlreadyExistException e) {
                model.addAttribute("emailexists", "User with such email address already exists.");
                return "register";
        }


        if(!result.hasErrors() ){
            try {
                user=userService.registerUser(dto);
            }catch (RegisterFailedException e){
                return "register";
            }

            if(user!=null) {
                return "redirect:/?register=success";
            }
        }
        return "register";

    }

    @GetMapping("/home/settings")
    public String settings(@AuthenticationPrincipal CurrentUser currentUser, Model model,
                           @RequestParam(required = false) String update, @RequestParam(required = false) String password) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        model.addAttribute("dto", new RegisterDto());

        if (update!=null) {
            model.addAttribute("update", "Details changed successfully!");
        }

        if (password!=null) {
            model.addAttribute("password", "Password changed successfully!");
        }

        return "settings"; }


    @PostMapping("/home/settings")
    public String updateUser(@AuthenticationPrincipal CurrentUser currentUser, @Valid User user, BindingResult result, Model model) {
        model.addAttribute("dto", new RegisterDto());

        try {
            userService.userExist(user.getUserName());
        } catch (UserAlreadyExistException e) {
            if (!user.getUserName().equals(currentUser.getUser().getUserName())) {
                model.addAttribute("userexists", "User with that user name already exists.");
                return "settings";
            }
        }

        try {
            userService.emailExist(user.getEmail());
        } catch (EmailAlreadyExistException e) {
            if (!user.getEmail().equals(currentUser.getUser().getEmail())) {
                model.addAttribute("emailexists", "User with such email address already exists.");
                return "settings";
            }
        }

        if(!result.hasErrors()) {
            userService.update(user, currentUser);
            return "redirect:settings?update=success";
        }

        return "settings";
    }

    @PostMapping("/home/changepassword")
    public String updatePassword(@ModelAttribute("dto") @Valid RegisterDto dto, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser,
                                 @RequestParam String currentPassword, Model model) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);

        if(!dto.getPassword().equals(dto.getConfirm_password())) {
            model.addAttribute("match", "Password doesn't match.");
        }

        try {
            userService.confirmPassword(user, currentPassword);
        } catch (ConfirmationFailedException ex) {
            model.addAttribute("failed", "Password doesn't match.");
            return "settings";
        }

        if(!result.hasErrors()) {
            try {
                userService.updatePassword(dto, currentUser);
                return "redirect:settings?password=success";
            } catch (ConfirmationFailedException ex) {
                return "settings";
            }
        }

        return "settings";
    }


    @GetMapping("home/delete")
    public String deleteUserForm () {
        return "delete";
    }

    @PostMapping("home/delete")
    public String deleteUser (@AuthenticationPrincipal CurrentUser currentUser, @RequestParam String password, Model model) {
        User user = currentUser.getUser();

        try {
            userService.confirmPassword(user, password);
            userService.delete(user);
            } catch (ConfirmationFailedException ex) {
            model.addAttribute("failed", "Password doesn't match.");
            return "delete";
        }

        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/?delete=success";
    }
}