package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORIES;

public class RedirectToHomePageCommandIml implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) {

        List<Category> categories = CRUDUtils.getAllCategories();
        request.setAttribute(CATEGORIES.getValue(), categories);
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}
