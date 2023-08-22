package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.repositories.CategoryRepository;
import by.teachmeskills.shop.repositories.impl.CategoryRepositoryImpl;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ImageService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORIES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private final ImageService imageService = new ImageServiceImpl();

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

    @Override
    public  void getCategory(HttpServletRequest request) {
        List<Category> categories = categoryRepository.read();
        List<Image> images = new ArrayList<>();
        for (Category category : categories) {
            images.add(imageService.getImageByCategoryId(category.getId()));
        }
        request.setAttribute(CATEGORIES.getValue(), categories);
        request.setAttribute(IMAGES.getValue(), images);
    }

}
