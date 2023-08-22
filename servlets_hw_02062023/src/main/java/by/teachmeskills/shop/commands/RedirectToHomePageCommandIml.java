package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.ImageServiceImpl;
import by.teachmeskills.shop.utils.FillingStorePage;
import jakarta.servlet.http.HttpServletRequest;

public class RedirectToHomePageCommandIml implements BaseCommand {
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        categoryService.getCategory(request);

//        FillingStorePage.showCategories(request, categoryService, imageService);
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}
