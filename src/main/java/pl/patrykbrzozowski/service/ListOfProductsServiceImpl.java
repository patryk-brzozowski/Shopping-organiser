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
    public void addNewList (User user) {
        ListOfProducts list = new ListOfProducts();
        list.setUser(user);
        list.setTitle("Untitled");

        listOfProductsRepository.save(list);
    }
}
