package by.teachmeskills.shop.services;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.domain.Product;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    Product getProductById(int id);

    List<Product> getProductsByCategoryId(int categoryId);
    void getProduct (HttpServletRequest request, int categoryId);
    void getProductShoppingCart (HttpServletRequest request, Cart shoppingCart);
    List<Product> getProductsBySearchParameter(String parameter);

}
