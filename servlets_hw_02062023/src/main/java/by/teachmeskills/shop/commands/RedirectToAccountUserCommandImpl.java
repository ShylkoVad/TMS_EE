package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Order;
import by.teachmeskills.shop.domain.User;
import by.teachmeskills.shop.services.OrderService;
import by.teachmeskills.shop.services.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.teachmeskills.shop.enums.PagesPathEnum.USER_ACCOUNT_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.ORDERS;
import static by.teachmeskills.shop.enums.RequestParamsEnum.USER;

public class RedirectToAccountUserCommandImpl implements BaseCommand {
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER.getValue());
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        request.setAttribute(ORDERS.getValue(), orders);

        return USER_ACCOUNT_PAGE.getPath();
    }
}