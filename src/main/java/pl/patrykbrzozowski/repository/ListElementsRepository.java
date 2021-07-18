package pl.patrykbrzozowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.patrykbrzozowski.model.ListElement;

import java.util.List;


public interface ListElementsRepository extends JpaRepository<ListElement, Long> {

    List<ListElement> findByListOfProducts_Id(long id);

    void removeAllByListOfProducts_Id(long listId);
}
