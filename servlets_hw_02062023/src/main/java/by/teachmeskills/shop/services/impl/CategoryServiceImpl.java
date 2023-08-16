package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.repositories.CategoryRepository;
import by.teachmeskills.shop.repositories.impl.CategoryRepositoryImpl;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORY_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCTS;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public Category create(Category entity) {
        return categoryRepository.create(entity);
    }

    @Override
    public List<Category> read() {
        return categoryRepository.read();
    }

    @Override
    public Category update(Category entity) {
        return categoryRepository.update(entity);
    }

    @Override
    public void delete(int id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id);
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
