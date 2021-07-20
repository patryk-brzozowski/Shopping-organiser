package pl.patrykbrzozowski.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    String description;
    String shop;
    @Min(0)
    double quantity;
    @Min(0)
    float price;

    @ManyToOne
    @JoinColumn(name = "list_id")
    @NotNull
    private ListOfProducts listOfProducts;
}
