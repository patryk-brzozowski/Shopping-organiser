package pl.patrykbrzozowski.service;

import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.model.User;

import java.util.List;

public interface ListOfProductsService {

    List<ListOfProducts> getAllUserLists(User user);

    ListOfProducts getListById(long id);

    void addNewList (User user);

    void deleteList(long listId);

    void changeTitle(long listId, String title);

}
