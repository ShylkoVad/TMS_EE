package by.teachmeskills.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private int id;
    private int userId;
    private LocalDateTime createdAt;
    private List<Product> productList;
    private double price;

    public Order(User user, List<Product> productList, double price) {
        this.userId = user.getId();
        createdAt = LocalDateTime.now();
        this.productList = productList;
        this.price = price;
    }

    public Order(int id, int userId, LocalDateTime createdAt, double price) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.price = price;
    }

    public Order(int id, int userId, double price) {
        this.id = id;
        this.userId = userId;
        this.price = price;
    }
}
