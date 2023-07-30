package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.shop.enums.PagesPathEnum.SHOPPING_CART_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART_PRODUCTS;

public class RemoveProductFromShoppingCartCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());

        Cart shoppCart = (Cart) session.getAttribute(SHOPPING_CART.getValue());

        shoppCart.removeProduct(Integer.parseInt(productId));
        session.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), shoppCart.getProducts());

        return SHOPPING_CART_PAGE.getPath();
    }
}