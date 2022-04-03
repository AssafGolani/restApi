package restapi.webapp;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Product implements Comparable<Product> {
    private @Id @GeneratedValue Long id;
    private String productName;
    private String category;
    private double price;

    // default constructor for Spring
    public Product() {
    }

    public Product(String productName, String category, double price) {
        this.productName = productName;
        this.category = category;
        this.price = price;
    }


    @Override
    public int compareTo(Product otherProduct) {
        return Double.compare(this.getPrice(), otherProduct.getPrice());
    }
}
