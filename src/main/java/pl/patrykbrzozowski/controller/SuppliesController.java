package pl.patrykbrzozowski.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.patrykbrzozowski.model.ListElement;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.model.ListOfSupplies;
import pl.patrykbrzozowski.security.CurrentUser;
import pl.patrykbrzozowski.service.ListElementService;
import pl.patrykbrzozowski.service.ListOfProductsService;
import pl.patrykbrzozowski.service.ListOfSuppliesService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/home")
public class SuppliesController {
    private final ListOfSuppliesService listOfSuppliesService;
    private final ListElementService listElementService;
    private final ListOfProductsService listOfProductsService;

    public SuppliesController (ListOfSuppliesService listOfSuppliesService, ListElementService listElementService
    ,ListOfProductsService listOfProductsService) {
        this.listOfSuppliesService = listOfSuppliesService;
        this.listElementService = listElementService;
        this.listOfProductsService = listOfProductsService;
    }

    @GetMapping("/supplies")
    public String supplies(@AuthenticationPrincipal CurrentUser currentUser, Model model, @RequestParam (required = false) String edition) {
        ListOfSupplies listOfSupplies = listOfSuppliesService.getListOfSupplies(currentUser.getUser());
        model.addAttribute("listOfSupplies", listOfSupplies);

        List<ListElement> listOfProducts = listOfSupplies.getElements();
        for (int i = 0; i < listOfProducts.size(); i++) {
            model.addAttribute("product"+i , listOfProducts.get(i));
        }

        if (edition!=null && edition.equals("success")) {
            model.addAttribute("success", "The row has been successfully edited!");
        } else if (edition!=null && edition.equals("failed")) {
            model.addAttribute("failed", "Failed to edit row. Please enter valid data.");
        }

        return "supplies"; }

    @PostMapping("/addproducttosupplies")
    public String addProduct(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam String description) {
        ListOfSupplies listOfSupplies = listOfSuppliesService.getListOfSupplies(currentUser.getUser());
        listElementService.addNewProductToSupplies(listOfSupplies, description);

        return "redirect:/home/supplies"; }

    @RequestMapping("/addtohistoryandsupplies")
    String addToHistoryAndSupplies(@AuthenticationPrincipal CurrentUser currentUser, long listId) {
        ListOfProducts listOfProducts = listOfProductsService.getListById(listId);
        listOfProducts.setActive("no");
        listOfProducts.setDate(LocalDate.now());

        List <ListElement> listElements = listOfProducts.getElements();

        ListOfSupplies listOfSupplies = listOfSuppliesService.getListOfSupplies(currentUser.getUser());
        List <ListElement> listOfSuppliesElements = listOfSupplies.getElements();

        for (ListElement element : listElements) {
            if (listOfSuppliesElements.contains(element)) {
                int index = listOfSuppliesElements.indexOf(element);
                ListElement elementToUpdate = listOfSuppliesElements.get(index);
                elementToUpdate.setQuantity(elementToUpdate.getQuantity() + element.getQuantity());

                listOfSuppliesElements.set(index, elementToUpdate);
                listOfSupplies.setElements(listOfSuppliesElements);
            } else {
                listElementService.addNewProductToSuppliesWithQuantity(listOfSupplies, element.getDescription(), element.getQuantity());
            }
        }

        listOfProductsService.saveList(listOfProducts);
        return "redirect:/home?closed=true";
    }

}
