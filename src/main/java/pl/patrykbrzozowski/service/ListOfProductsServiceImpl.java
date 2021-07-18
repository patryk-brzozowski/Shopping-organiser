package pl.patrykbrzozowski.service;

import org.springframework.stereotype.Service;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.repository.ListOfProductsRepository;

import java.util.List;

@Service
public class ListOfProductsServiceImpl implements ListOfProductsService {

    private final ListOfProductsRepository listOfProductsRepository;

    public ListOfProductsServiceImpl(ListOfProductsRepository listOfProductsRepository) {
        this.listOfProductsRepository = listOfProductsRepository;
    }

    @Override
    public List<ListOfProducts> getAllUserLists(User user) {
        return listOfProductsRepository.findByUser(user);
    }

    @Override
    public ListOfProducts getListById(long id) {
        return listOfProductsRepository.findListOfProductsById(id);
    }

    @Override
    public void addNewList (User user) {
        ListOfProducts list = new ListOfProducts();
        list.setUser(user);
        list.setTitle("Untitled");

        listOfProductsRepository.save(list);
    }

    @Override
    public void deleteList(long listId) {
        listOfProductsRepository.deleteById(listId);
    }

    @Override
    public void changeTitle(long listId, String title) {
        ListOfProducts list = listOfProductsRepository.findListOfProductsById(listId);
        list.setTitle(title);

        listOfProductsRepository.save(list);
    }
}
