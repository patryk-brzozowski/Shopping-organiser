package pl.patrykbrzozowski.service;

import pl.patrykbrzozowski.model.ListOfSupplies;
import pl.patrykbrzozowski.model.User;


public interface ListOfSuppliesService {

    ListOfSupplies getListOfSupplies (User user);

    ListOfSupplies getListOfSuppliesById (long id);

    void saveList(ListOfSupplies listOfSupplies);

}
