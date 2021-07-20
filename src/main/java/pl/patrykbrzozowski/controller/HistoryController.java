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
import pl.patrykbrzozowski.service.ListOfProductsService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HistoryController {

    private final ListOfProductsService listOfProductsService;

    public HistoryController(ListOfProductsService listOfProductsService) {
        this.listOfProductsService = listOfProductsService;
    }

    @GetMapping("/history")
    public String home(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        List<ListOfProducts> listOfProducts = listOfProductsService.getAllUserLists(currentUser.getUser());
        listOfProducts.removeIf(el -> el.getActive().equals("yes"));
        model.addAttribute("userLists", listOfProducts);

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

        model.addAttribute("list", list);

        return "historydetails";
    }

    @PostMapping("/changedate")
    String changeDate (@RequestParam long listId, String date, HttpServletRequest request) {
        listOfProductsService.changeDate(listId, date);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
