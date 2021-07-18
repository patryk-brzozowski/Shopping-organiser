package pl.patrykbrzozowski.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="list_elements")
public class ListElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String description;
    @Min(0)
    double quantity;
    @Min(0)
    double price;

    @ManyToOne
    @JoinColumn(name = "list_id")
    @NotNull
    private ListOfProducts listOfProducts;
}
