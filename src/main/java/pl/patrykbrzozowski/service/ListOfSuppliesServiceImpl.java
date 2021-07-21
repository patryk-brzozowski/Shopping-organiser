package pl.patrykbrzozowski.service;

import org.springframework.stereotype.Service;
import pl.patrykbrzozowski.model.ListOfSupplies;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.repository.ListOfSuppliesRepository;

import java.util.List;

@Service
public class ListOfSuppliesServiceImpl implements ListOfSuppliesService{

    private final ListOfSuppliesRepository listOfSuppliesRepository;

     public ListOfSuppliesServiceImpl (ListOfSuppliesRepository listOfSuppliesRepository) {
         this.listOfSuppliesRepository = listOfSuppliesRepository;
     }

     @Override
     public ListOfSupplies getListOfSupplies (User user) {
         return listOfSuppliesRepository.getListOfSuppliesByUser(user);
     }

    @Override
    public ListOfSupplies getListOfSuppliesById(long id) {
        return listOfSuppliesRepository.getListOfSuppliesById(id);
    }

    @Override
    public void saveList(ListOfSupplies listOfSupplies) {
         listOfSuppliesRepository.save(listOfSupplies);
    }
}
