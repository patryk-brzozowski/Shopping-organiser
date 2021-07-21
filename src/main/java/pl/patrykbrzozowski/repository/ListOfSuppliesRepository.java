package pl.patrykbrzozowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.patrykbrzozowski.model.ListOfSupplies;
import pl.patrykbrzozowski.model.User;

public interface ListOfSuppliesRepository extends JpaRepository<ListOfSupplies, Long> {

    ListOfSupplies getListOfSuppliesByUser(User user);

    ListOfSupplies getListOfSuppliesById(long id);

}
