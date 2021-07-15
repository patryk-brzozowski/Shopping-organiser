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
    public String welcome() { return "welcome"; }

    @GetMapping("/home")
    public String home() { return "home"; }

}