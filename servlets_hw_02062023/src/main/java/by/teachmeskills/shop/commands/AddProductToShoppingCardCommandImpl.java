package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.shop.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;

public class AddProductToShoppingCardCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());

        Cart shoppCart;
        Object sessionShoppCart = session.getAttribute(SHOPPING_CART.getValue());
        if (sessionShoppCart != null) {
            shoppCart = (Cart) sessionShoppCart;
        } else {
            shoppCart = new Cart();
            session.setAttribute(SHOPPING_CART.getValue(), shoppCart);
        }

        Product product = CRUDUtils.getProductById(productId);
        shoppCart.addProduct(product);
        request.setAttribute(PRODUCT.getValue(), product);

        return PRODUCT_PAGE.getPath();
    }
}