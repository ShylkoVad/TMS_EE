package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.impl.ImageServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static by.teachmeskills.shop.enums.PagesPathEnum.SHOPPING_CART_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART_PRODUCTS;

public class RedirectToShoppingCartCommandImpl implements BaseCommand {
    private final ImageService imageService = new ImageServiceImpl();


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart shoppingCart = (Cart) session.getAttribute(SHOPPING_CART.getValue());

        if (shoppingCart == null) {
            request.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), "");
        } else {
            List<Product> products = shoppingCart.getProducts();
            List<List<Image>> images = new ArrayList<>();
            for (Product product : products) {
                images.add(imageService.getImagesByProductId(product.getId()));
            }
            request.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), products);
            request.setAttribute(IMAGES.getValue(), images.stream().flatMap(Collection::stream).collect(Collectors.toList()));
        }

        return SHOPPING_CART_PAGE.getPath();
    }
}
