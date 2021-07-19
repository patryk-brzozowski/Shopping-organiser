package pl.patrykbrzozowski.service;

import org.springframework.stereotype.Service;
import pl.patrykbrzozowski.model.ListElement;
import pl.patrykbrzozowski.repository.ListElementsRepository;
import pl.patrykbrzozowski.repository.ListOfProductsRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ListElementServiceImpl implements ListElementService {
    private final ListElementsRepository listElementsRepository;
    private final ListOfProductsRepository listOfProductsRepository;

    public ListElementServiceImpl(ListElementsRepository listElementsRepository, ListOfProductsRepository listOfProductsRepository) {
        this.listElementsRepository = listElementsRepository;
        this.listOfProductsRepository = listOfProductsRepository;
    }


    @Override
    public List<ListElement> getAllProducts(long id) {
        return listElementsRepository.findByListOfProducts_Id(id);
    }

    @Override
    public void addNewProduct(long listId, String description) {
        ListElement element = new ListElement();
        element.setDescription(description);
        element.setListOfProducts(listOfProductsRepository.getById(listId));

        listElementsRepository.save(element);
    }

    @Override
    public void updateProduct(ListElement element) {

        listElementsRepository.save(element);
    }

    @Override
    public void deleteProduct(long productId) {
        listElementsRepository.deleteById(productId);
    }

    @Override
    @Transactional
    public void deleteAllProductsByList(long listId) {
        listElementsRepository.removeAllByListOfProducts_Id(listId);
    }
}
