package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ImageServiceImpl;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static by.teachmeskills.shop.enums.PagesPathEnum.CATEGORY_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORY_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCTS;

public class RedirectToCategoryPageImpl implements BaseCommand {
    private final ProductService productService = new ProductServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        int categoryId = Integer.parseInt(request.getParameter(CATEGORY_ID.getValue()));

        List<Product> products = productService.getProductsByCategoryId(categoryId);
        List<List<Image>> images = new ArrayList<>();
        for (Product product : products) {
            images.add(imageService.getImagesByProductId(product.getId()));
        }
        request.setAttribute(PRODUCTS.getValue(), products);
        request.setAttribute(IMAGES.getValue(),
                images.stream().flatMap(Collection::stream).collect(Collectors.toList()));

        return CATEGORY_PAGE.getPath();
    }
}
