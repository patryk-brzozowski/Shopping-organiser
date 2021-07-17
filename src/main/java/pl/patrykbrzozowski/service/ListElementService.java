package pl.patrykbrzozowski.service;

import pl.patrykbrzozowski.model.ListElement;
import pl.patrykbrzozowski.model.ListOfProducts;

import java.util.List;

public interface ListElementService {

    List<ListElement> getAllProducts(long id);

    void addNewProduct (long listId, String description);

    void deleteProduct(long productId);
}