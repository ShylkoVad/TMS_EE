package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ImageServiceImpl;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.shop.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT_ID;

public class RedirectToProductCommandImpl implements BaseCommand {
    private final ProductService productService = new ProductServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {

        int productId = Integer.parseInt(request.getParameter(PRODUCT_ID.getValue()));

        request.setAttribute(PRODUCT.getValue(), productService.getProductById(productId));
        request.setAttribute(IMAGES.getValue(), imageService.getImagesByProductId(productId));

        return PRODUCT_PAGE.getPath();
    }
}

