package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.domain.Order;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.domain.User;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static by.teachmeskills.shop.enums.PagesPathEnum.SHOPPING_CART_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;
import static by.teachmeskills.shop.enums.RequestParamsEnum.USER;

@Slf4j
public class CheckoutCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        Cart shoppingCart = (Cart) session.getAttribute(SHOPPING_CART.getValue());
        User user = (User) session.getAttribute(USER.getValue());

        List<Product> productList = shoppingCart.getProducts();

        if (productList.isEmpty()) {
            return SHOPPING_CART_PAGE.getPath();
        }

        Order order = new Order(user, productList, shoppingCart.getTotalPrice());
        CRUDUtils.addOrder(order);
        log.info("New order registered");
        shoppingCart.clear();
        return SHOPPING_CART_PAGE.getPath();
    }
}