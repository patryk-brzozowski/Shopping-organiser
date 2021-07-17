package pl.patrykbrzozowski.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.security.CurrentUser;
import pl.patrykbrzozowski.service.ListElementService;
import pl.patrykbrzozowski.service.ListOfProductsService;

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

    @PostMapping("/addproduct")
    String addNewProduct (@RequestParam long listId, @RequestParam String description) {
        listElementService.addNewProduct(listId, description);

        return "redirect:/home";
    }

    @PostMapping("/deleteproduct")
    String deleteProduct (@RequestParam long productId) {
        listElementService.deleteProduct(productId);

        return "redirect:/home";
    }
}
