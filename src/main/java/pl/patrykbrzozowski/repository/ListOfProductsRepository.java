package pl.patrykbrzozowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.patrykbrzozowski.model.ListOfProducts;
import pl.patrykbrzozowski.model.User;

import java.util.List;

public interface ListOfProductsRepository extends JpaRepository<ListOfProducts, Long> {

    List<ListOfProducts> findByUser(User user);

    ListOfProducts findListOfProductsById (long id);
}
