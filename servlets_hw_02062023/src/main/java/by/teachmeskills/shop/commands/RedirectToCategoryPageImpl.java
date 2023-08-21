package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ImageServiceImpl;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import by.teachmeskills.shop.utils.FillingStorePage;
import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.shop.enums.PagesPathEnum.CATEGORY_PAGE;

public class RedirectToCategoryPageImpl implements BaseCommand {
    private final ProductService productService = new ProductServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        FillingStorePage.showProduct(request, productService, imageService);

        return CATEGORY_PAGE.getPath();
    }
}
