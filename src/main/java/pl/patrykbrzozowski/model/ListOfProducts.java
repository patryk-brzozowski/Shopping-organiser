package pl.patrykbrzozowski.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="lists")
public class ListOfProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "listOfProducts")
    private List<ListElement> elements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListOfProducts)) return false;
        ListOfProducts list = (ListOfProducts) o;
        return id.equals(list.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
