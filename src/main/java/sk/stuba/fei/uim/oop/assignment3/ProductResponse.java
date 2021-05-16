package sk.stuba.fei.uim.oop.assignment3;


import lombok.Getter;

@Getter
public class ProductResponse {
    private String name;
    private String description;
    private Long amount;
    private String unit;
    private float price;

    public ProductResponse(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.unit = product.getUnit();
        this.price = product.getPrice();
    }
}
