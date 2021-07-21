package pl.patrykbrzozowski.service;

import pl.patrykbrzozowski.model.ListElement;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.model.ListOfSupplies;

import java.util.List;

public interface ListElementService {

    List<ListElement> getAllProducts(long id);

    void addNewProduct (long listId, String description);

    void updateProduct(ListElement element);

    void deleteProduct(long productId);

    void deleteAllProductsByList(long listId);

    void addNewProductToSupplies(ListOfSupplies listOfSupplies, String description);

    void addNewProductToSuppliesWithQuantity(ListOfSupplies listOfSupplies, String description, double quantity);
}
