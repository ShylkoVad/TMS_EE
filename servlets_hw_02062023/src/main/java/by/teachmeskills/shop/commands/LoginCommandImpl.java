package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.User;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.shop.enums.PagesPathEnum.HOME_PAGE;
import static by.teachmeskills.shop.enums.PagesPathEnum.LOGIN_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORIES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.LOGIN;
import static by.teachmeskills.shop.enums.RequestParamsEnum.USER;

public class LoginCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(LOGIN.getValue());

        User user = CRUDUtils.getUser(email);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(USER.getValue(), user);
            request.setAttribute(CATEGORIES.getValue(), CRUDUtils.getAllCategories());
            return HOME_PAGE.getPath();
        } else {
            return LOGIN_PAGE.getPath();
        }

    }
}