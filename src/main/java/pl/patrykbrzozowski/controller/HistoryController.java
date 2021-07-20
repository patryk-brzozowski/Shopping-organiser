package pl.patrykbrzozowski.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.patrykbrzozowski.model.ListElement;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.security.CurrentUser;
import pl.patrykbrzozowski.service.ListOfProductsService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/home")
public class HistoryController {

    private final ListOfProductsService listOfProductsService;

    public HistoryController(ListOfProductsService listOfProductsService) {
        this.listOfProductsService = listOfProductsService;
    }

    @GetMapping("/history")
    public String history(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        List<ListOfProducts> listOfProducts = listOfProductsService.getAllUserLists(currentUser.getUser());
        listOfProducts.removeIf(el -> el.getActive().equals("yes"));

        BigDecimal roundedTotalPrice = listOfProductsService.calculatePrice(listOfProducts);

        model.addAttribute("userLists", listOfProducts);
        model.addAttribute("totalPrice", roundedTotalPrice);

        return "history";
    }

    @PostMapping("/history")
    public String historyFiltered(@AuthenticationPrincipal CurrentUser currentUser, Model model,
                                  @RequestParam String year, @RequestParam String month, @RequestParam String shop) {
        List<ListOfProducts> listOfProducts = listOfProductsService.getAllUserLists(currentUser.getUser());
        listOfProducts.removeIf(el -> el.getActive().equals("yes"));

        if (!year.equals("all")) {
            listOfProducts.removeIf(el->!String.valueOf(el.getDate().getYear()).equals(year));
        }

        if (!month.equals("all")) {
            listOfProducts.removeIf(el->!String.valueOf(el.getDate().getMonth()).equals(month));
        }

        if (!shop.equals("all")) {
            for (ListOfProducts list : listOfProducts) {
            if (shop.equals("")) {
                list.getElements().removeIf(el -> !(el.getShop()==null || el.getShop().isBlank()));
            } else {
                    list.getElements().removeIf(el -> el.getShop()==null || !el.getShop().equalsIgnoreCase(shop));
                }
            }
        }

        BigDecimal roundedTotalPrice = listOfProductsService.calculatePrice(listOfProducts);

        model.addAttribute("userLists", listOfProducts);
        model.addAttribute("totalPrice", roundedTotalPrice);

        return "history";
    }

    @RequestMapping("/addtohistory")
    String addToHistory(long listId) {
        ListOfProducts list = listOfProductsService.getListById(listId);
        list.setActive("no");
        list.setDate(LocalDate.now());

        listOfProductsService.saveList(list);
        return "redirect:/home?closed=true";
    }

    @GetMapping("/historydetails")
    String showListDetails (@AuthenticationPrincipal CurrentUser currentUser, @RequestParam long id, Model model) {
        ListOfProducts list = listOfProductsService.getListById(id);
        List<ListOfProducts> userList = listOfProductsService.getAllUserLists(currentUser.getUser());

        if (!userList.contains(list)) {
            return "redirect:/home";
        }

        BigDecimal roundedTotalPrice = listOfProductsService.calculatePrice(Arrays.asList(list));

        model.addAttribute("list", list);
        model.addAttribute("totalPrice", roundedTotalPrice);

        return "historydetails";
    }

    @PostMapping("/changedate")
    String changeDate (@RequestParam long listId, String date, HttpServletRequest request) {
        listOfProductsService.changeDate(listId, date);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
    @ModelAttribute("years")
    Set<String> years(@AuthenticationPrincipal CurrentUser currentUser) {
        List<ListOfProducts> listOfProducts = listOfProductsService.getAllUserLists(currentUser.getUser());
        listOfProducts.removeIf(el -> el.getActive().equals("yes"));
        Set<String> years = new HashSet<>();

        if (listOfProducts!=null) {
            listOfProducts.forEach(el -> years.add(String.valueOf(el.getDate().getYear())));
        }

        return years;
    }

    @ModelAttribute("months")
    Set<String> months(@AuthenticationPrincipal CurrentUser currentUser) {
        List<ListOfProducts> listOfProducts = listOfProductsService.getAllUserLists(currentUser.getUser());
        listOfProducts.removeIf(el -> el.getActive().equals("yes"));
        Set<String> months = new HashSet<>();

        if (listOfProducts!=null) {
            listOfProducts.forEach(el -> months.add(String.valueOf(el.getDate().getMonth())));
        }

        return months;
    }

    @ModelAttribute("shops")
    Set<String> shops(@AuthenticationPrincipal CurrentUser currentUser) {
        List<ListOfProducts> listOfProducts = listOfProductsService.getAllUserLists(currentUser.getUser());
        listOfProducts.removeIf(el -> el.getActive().equals("yes"));
        Set<String> shops = new HashSet<>();

        if (listOfProducts!=null) {
            for (ListOfProducts list: listOfProducts) {
                list.getElements().forEach(el -> shops.add(el.getShop()));
            }
        }

        return shops;
    }
}
