package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.shop.enums.PagesPathEnum.SHOPPING_CART_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART_PRODUCTS;

public class RemoveProductFromShoppingCartCommandImpl implements BaseCommand {
    private final ProductService productService = new ProductServiceImpl();


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());
        Cart shoppingCart = (Cart) session.getAttribute(SHOPPING_CART.getValue());

        shoppingCart.removeProduct(Integer.parseInt(productId));
        request.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), shoppingCart.getProducts());

        productService.getProductShoppingCart(request, shoppingCart);
        return SHOPPING_CART_PAGE.getPath();
    }
}