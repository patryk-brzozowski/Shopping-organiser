package pl.patrykbrzozowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @GetMapping("/")
    public String welcome() { return "welcome"; }

    @GetMapping("/home")
    public String home() { return "home"; }
}