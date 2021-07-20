package pl.patrykbrzozowski.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.patrykbrzozowski.model.ListElement;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.security.CurrentUser;
import pl.patrykbrzozowski.service.ListElementService;
import pl.patrykbrzozowski.service.ListOfProductsService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    String deleteList (@RequestParam long listId, HttpServletRequest request) {
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
        if(referer.contains("?closed=true")) {
            referer = referer.replaceFirst("\\?closed=true", "");
        }
        return "redirect:" + referer;
    }

    @PostMapping("/editproducts")
    public String editProduct(@Valid ListElement element, BindingResult result,  HttpServletRequest request) {
        String referer = request.getHeader("Referer");

        if(referer.contains("&edition=success")) {
            referer = referer.replaceFirst("&edition=success", "");
        } else if (referer.contains("&edition=failed")) {
            referer = referer.replaceFirst("&edition=failed", "");
        }

        if(!result.hasErrors() ){
            listElementService.updateProduct(element);
                return "redirect:" + referer + "&edition=success";
        }

        return "redirect:" + referer + "&edition=failed";
    }

    @GetMapping("/editproducts")
    String editProducts (@AuthenticationPrincipal CurrentUser currentUser, @RequestParam long id, @RequestParam (required = false) String edition, Model model) {
        ListOfProducts list = listOfProductsService.getListById(id);
        List<ListOfProducts> userList = listOfProductsService.getAllUserLists(currentUser.getUser());

        if (!userList.contains(list)) {
            return "redirect:/home";
        }

        model.addAttribute("list", listOfProductsService.getListById(id));
        List<ListElement> listOfProducts = list.getElements();
        for (int i = 0; i < listOfProducts.size(); i++) {
            model.addAttribute("product"+i , listOfProducts.get(i));
        }

        if (edition!=null && edition.equals("success")) {
            model.addAttribute("success", "The row has been successfully edited!");
        } else if (edition!=null && edition.equals("failed")) {
            model.addAttribute("failed", "Failed to edit row. Please enter valid data.");
        }

        return "productedition";
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
