package pl.patrykbrzozowski.service;

import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.model.User;

import java.util.List;

public interface ListOfProductsService {

    List<ListOfProducts> getAllUserLists(User user);

    void addNewList (User user);

}
