package pl.patrykbrzozowski.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.security.CurrentUser;
import pl.patrykbrzozowski.service.ListElementService;
import pl.patrykbrzozowski.service.ListOfProductsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/home")
public class ListsController {
    private final ListOfProductsService listOfProductsService;
    private final ListElementService listElementService;

    public ListsController (ListOfProductsService listOfProductsService, ListElementService listElementService) {
        this.listOfProductsService = listOfProductsService;
        this.listElementService = listElementService;
    }

    @PostMapping("/add")
    String addNewList (@AuthenticationPrincipal CurrentUser currentUser) {
        listOfProductsService.addNewList(currentUser.getUser());

        return "redirect:/home";
    }

    @PostMapping("/deletelist")
    String deleteList (@RequestParam long listId) {
        listOfProductsService.deleteList(listId);

        return "redirect:/home";
    }

    @GetMapping("/details")
    String showListDetails (@AuthenticationPrincipal CurrentUser currentUser, @RequestParam long id, Model model) {
        ListOfProducts list = listOfProductsService.getListById(id);
        List<ListOfProducts> userList = listOfProductsService.getAllUserLists(currentUser.getUser());

        if (!userList.contains(list)) {
            return "redirect:/home";
        }

        model.addAttribute("list", list);

        return "listdetails";
    }

    @PostMapping("/changetitle")
    String changeTitle (@RequestParam long listId, String title, HttpServletRequest request) {
        listOfProductsService.changeTitle(listId, title);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/addproduct")
    String addNewProduct (@RequestParam long listId, @RequestParam String description, HttpServletRequest request) {
        listElementService.addNewProduct(listId, description);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/deleteproduct")
    String deleteProduct (@RequestParam long productId, HttpServletRequest request) {
        listElementService.deleteProduct(productId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/deleteallproducts")
    String deleteAllProducts (@RequestParam long listId, HttpServletRequest request) {
        listElementService.deleteAllProductsByList(listId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
