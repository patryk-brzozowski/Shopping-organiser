package pl.patrykbrzozowski.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="list_elements")
public class ListElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    String description;
    String shop;
    @Min(0)
    double quantity;
    @Min(0)
    float price;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ListOfProducts listOfProducts;

    @ManyToOne
    @JoinColumn(name = "list_of_supplies_id")
    private ListOfSupplies listOfSupplies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListElement)) return false;
        ListElement element = (ListElement) o;
        return this.getDescription().equalsIgnoreCase(((ListElement) o).getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
