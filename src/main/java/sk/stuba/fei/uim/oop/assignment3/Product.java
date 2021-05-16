package sk.stuba.fei.uim.oop.assignment3;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class  Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private Long amount;
    private String unit;
    private float price;
}
