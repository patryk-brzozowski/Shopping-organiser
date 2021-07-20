package pl.patrykbrzozowski.service;

import org.springframework.stereotype.Service;
import pl.patrykbrzozowski.model.ListElement;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.repository.ListOfProductsRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
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
        list.setActive("yes");

        listOfProductsRepository.save(list);
    }

    @Override
    public void saveList(ListOfProducts listOfProducts) {
        listOfProductsRepository.save(listOfProducts);
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

    @Override
    public void changeDate(long listId, String date) {
        ListOfProducts list = listOfProductsRepository.findListOfProductsById(listId);
        list.setDate(LocalDate.parse(date));

        listOfProductsRepository.save(list);
    }

    @Override
    public BigDecimal calculatePrice(List<ListOfProducts> listOfProducts) {
        double totalPrice = 0;
        for(ListOfProducts list: listOfProducts){
            totalPrice += list.getElements().stream()
                    .mapToDouble((ListElement::getPrice))
                    .sum();
        }
        BigDecimal roundedTotalPrice = BigDecimal.valueOf(totalPrice);
        roundedTotalPrice = roundedTotalPrice.setScale(2, RoundingMode.HALF_UP);
        return roundedTotalPrice;
    }
}
