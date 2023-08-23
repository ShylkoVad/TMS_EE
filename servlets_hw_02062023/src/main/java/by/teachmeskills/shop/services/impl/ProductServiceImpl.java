package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.repositories.ProductRepository;
import by.teachmeskills.shop.repositories.impl.ProductRepositoryImpl;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCTS;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository = new ProductRepositoryImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public Product create(Product entity) {
        return productRepository.create(entity);
    }

    @Override
    public List<Product> read() {
        return productRepository.read();
    }

    @Override
    public Product update(Product entity) {
        return productRepository.update(entity);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public void getProduct(HttpServletRequest request, int categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        List<List<Image>> images = new ArrayList<>();
        for (Product product : products) {
            images.add(imageService.getImagesByProductId(product.getId()));
        }
        request.setAttribute(PRODUCTS.getValue(), products);
        request.setAttribute(IMAGES.getValue(), images.stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }

    @Override
    public void getProductShoppingCart(HttpServletRequest request, Cart shoppingCart) {
        List<Product> products = shoppingCart.getProducts();
        List<List<Image>> images = new ArrayList<>();
        for (Product product : products) {
            images.add(imageService.getImagesByProductId(product.getId()));
        }
        shoppingCart.shoppingCartProducts(request, products);
        request.setAttribute(IMAGES.getValue(), images.stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }
    @Override
    public List<Product> getProductsBySearchParameter(String parameter) {
        return productRepository.findBySearchParameter(parameter);
    }
}
