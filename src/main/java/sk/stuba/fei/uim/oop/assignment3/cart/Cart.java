package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    @OneToMany
    private List<ProductInCart> shoppingList = new ArrayList<>();
    private boolean payed;
}
