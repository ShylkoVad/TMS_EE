package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.repositories.ProductRepository;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.teachmeskills.shop.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.shop.enums.PagesPathEnum.SEARCH_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCTS;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;

    public ProductServiceImpl(ProductRepository productRepository, ImageService imageService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
    }

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
    public ModelAndView getProductsBySearchParameter(String parameter) {
        ModelMap model = new ModelMap();
        List<Product> products = productRepository.findBySearchParameter(parameter);

        if (!products.isEmpty()) {
            List<List<Image>> images = new ArrayList<>();

            for (Product product : products) {
                images.add(imageService.getImagesByProductId(product.getId()));
            }

            model.addAttribute(PRODUCTS.getValue(), products);
            model.addAttribute(IMAGES.getValue(), images.stream().flatMap(Collection::stream).collect(Collectors.toList()));

            return new ModelAndView(SEARCH_PAGE.getPath(), model);
        }

        return new ModelAndView(SEARCH_PAGE.getPath(), model);
    }

    @Override
    public ModelAndView getProductData(int id) {
        ModelMap model = new ModelMap();
        Product product = productRepository.findById(id);
        if (Optional.ofNullable(product).isPresent()) {
            model.addAttribute(PRODUCT.getValue(), product);
            model.addAttribute(IMAGES.getValue(), imageService.getImagesByProductId(id));
        }
        return new ModelAndView(PRODUCT_PAGE.getPath(), model);
    }
}
