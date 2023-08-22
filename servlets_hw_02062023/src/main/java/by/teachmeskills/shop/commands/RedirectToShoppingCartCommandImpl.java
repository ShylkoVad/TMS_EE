package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.shop.enums.PagesPathEnum.SHOPPING_CART_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART_PRODUCTS;

public class RedirectToShoppingCartCommandImpl implements BaseCommand {
    private final ProductService productService = new ProductServiceImpl();


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart shoppingCart = (Cart) session.getAttribute(SHOPPING_CART.getValue());

        if (shoppingCart == null) {
            request.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), "");
        } else {
            productService.getProductShoppingCart(request, shoppingCart);
        }

        return SHOPPING_CART_PAGE.getPath();
    }
}
