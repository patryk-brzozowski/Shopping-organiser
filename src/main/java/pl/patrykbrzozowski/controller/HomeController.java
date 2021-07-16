package pl.patrykbrzozowski.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.security.CurrentUser;

import javax.validation.Valid;

@Controller
public class HomeController {

    @GetMapping("/")
    public String welcome(Model model, @RequestParam(required = false) String failed, @RequestParam(required = false) String delete,
                          @RequestParam(required = false) String logout, @RequestParam(required = false) String register) {
        if(failed!=null) {
            model.addAttribute("failed", "User name and/or password doesn't match. Try again.");
        }

        if(delete!=null) {
            model.addAttribute("delete", "The account has been deleted.");
        }

        if(logout!=null) {
            model.addAttribute("logout", "You have been successfully logged out.");
        }

        if(register!=null) {
            model.addAttribute("register", "Your account has been successfully created.");
        }

        return "welcome"; }

    @GetMapping("/home")
    public String home() { return "home"; }

}