package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class RedirectToHomePageCommandIml implements BaseCommand {
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        categoryService.getCategory(request);

        return PagesPathEnum.HOME_PAGE.getPath();
    }
}
