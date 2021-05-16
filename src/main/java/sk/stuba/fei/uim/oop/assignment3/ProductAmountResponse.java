package sk.stuba.fei.uim.oop.assignment3;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductAmountResponse{
    private Long amount;


    public ProductAmountResponse(Product product) {
        this.amount = product.getAmount();
    }
}
