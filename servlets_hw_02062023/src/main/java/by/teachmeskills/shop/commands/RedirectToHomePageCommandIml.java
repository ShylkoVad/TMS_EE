package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.ImageServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORIES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;

public class RedirectToHomePageCommandIml implements BaseCommand {
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {

        List<Category> categories = categoryService.read();
        List<Image> images = new ArrayList<>();

        for (Category category : categories) {
            images.add(imageService.getImageByCategoryId(category.getId()));
        }
        request.setAttribute(CATEGORIES.getValue(), categories);
        request.setAttribute(IMAGES.getValue(), images);
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}
