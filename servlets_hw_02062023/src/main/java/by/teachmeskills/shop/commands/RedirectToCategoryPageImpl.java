package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.teachmeskills.shop.enums.PagesPathEnum.CATEGORY_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORY_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCTS;

public class RedirectToCategoryPageImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String categoryId = request.getParameter(CATEGORY_ID.getValue());

        List<Product> products = CRUDUtils.getCategoryProducts(categoryId);
        request.setAttribute(PRODUCTS.getValue(), products);

        return CATEGORY_PAGE.getPath();
    }
}
