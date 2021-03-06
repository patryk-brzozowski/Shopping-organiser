package pl.patrykbrzozowski.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.security.CurrentUser;
import pl.patrykbrzozowski.service.ListOfProductsService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    private final ListOfProductsService listOfProductsService;

    public HomeController (ListOfProductsService listOfProductsService) {
        this.listOfProductsService = listOfProductsService;
    }

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
    public String home(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam(required = false) String closed, Model model) {
        List<ListOfProducts> listOfProducts = listOfProductsService.getAllUserLists(currentUser.getUser());
        listOfProducts.removeIf(el-> el.getActive().equals("no"));
        model.addAttribute("userLists", listOfProducts);

        if (closed!=null) {
            model.addAttribute("closed", "The list was closed successfully!");
        }

        return "home"; }

}