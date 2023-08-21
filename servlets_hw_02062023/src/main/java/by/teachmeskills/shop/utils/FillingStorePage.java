package by.teachmeskills.shop.utils;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static by.teachmeskills.shop.enums.RequestParamsEnum.*;

public class FillingStorePage {
    public static void showCategories(HttpServletRequest request, CategoryService categoryService, ImageService imageService) {
        List<Category> categories = categoryService.read();
        List<Image> images = new ArrayList<>();
        for (Category category : categories) {
            images.add(imageService.getImageByCategoryId(category.getId()));
        }
        request.setAttribute(CATEGORIES.getValue(), categories);
        request.setAttribute(IMAGES.getValue(), images);
    }
    public static void showShoppingCartProducts (HttpServletRequest request, ImageService imageService, Cart shoppingCart) {
        List<Product> products = shoppingCart.getProducts();
        List<List<Image>> images = new ArrayList<>();
        for (Product product : products) {
            images.add(imageService.getImagesByProductId(product.getId()));
        }
        request.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), products);
        request.setAttribute(IMAGES.getValue(), images.stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }
    public static void showProduct(HttpServletRequest request, ProductService productService, ImageService imageService) {
        int categoryId = Integer.parseInt(request.getParameter(CATEGORY_ID.getValue()));

        List<Product> products = productService.getProductsByCategoryId(categoryId);
        List<List<Image>> images = new ArrayList<>();
        for (Product product : products) {
            images.add(imageService.getImagesByProductId(product.getId()));
        }
        request.setAttribute(PRODUCTS.getValue(), products);
        request.setAttribute(IMAGES.getValue(),
                images.stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }
}
