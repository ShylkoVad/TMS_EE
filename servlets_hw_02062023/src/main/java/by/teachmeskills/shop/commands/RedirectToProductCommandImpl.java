package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.shop.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT_ID;

public class RedirectToProductCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String productId = request.getParameter(PRODUCT_ID.getValue());

        Product product = CRUDUtils.getProductById(productId);
        request.setAttribute(PRODUCT.getValue(), product);

        return PRODUCT_PAGE.getPath();
    }
}

