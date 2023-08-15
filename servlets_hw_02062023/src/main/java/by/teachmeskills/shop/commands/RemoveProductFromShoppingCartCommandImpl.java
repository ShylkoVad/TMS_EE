package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.exceptions.CommandException;
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
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART_PRODUCTS;

public class RemoveProductFromShoppingCartCommandImpl implements BaseCommand {
    private final ImageService imageService = new ImageServiceImpl();


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());
        Cart shoppCart = (Cart) session.getAttribute(SHOPPING_CART.getValue());

        shoppCart.removeProduct(Integer.parseInt(productId));
        request.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), shoppCart.getProducts());

        List<Product> products = shoppCart.getProducts();
        List<List<Image>> images = new ArrayList<>();
        for (Product product : products) {
            images.add(imageService.getImagesByProductId(product.getId()));
        }
        request.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), products);
        request.setAttribute(IMAGES.getValue(), images.stream().flatMap(Collection::stream).collect(Collectors.toList()));

        return SHOPPING_CART_PAGE.getPath();
    }
}