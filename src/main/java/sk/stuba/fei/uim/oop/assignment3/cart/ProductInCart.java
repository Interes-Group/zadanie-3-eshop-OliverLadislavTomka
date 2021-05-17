package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private Long amount;
}
