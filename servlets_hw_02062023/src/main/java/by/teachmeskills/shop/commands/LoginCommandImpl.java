package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.User;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.UserService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.ImageServiceImpl;
import by.teachmeskills.shop.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import static by.teachmeskills.shop.enums.PagesPathEnum.HOME_PAGE;
import static by.teachmeskills.shop.enums.PagesPathEnum.LOGIN_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.*;

@Slf4j
public class LoginCommandImpl implements BaseCommand {
    private final UserService userService = new UserServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(LOGIN.getValue());
        String password = request.getParameter(PASSWORD.getValue());
        User user = userService.getUserByEmailAndPassword(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(USER.getValue(), user);
//            FillingStorePage.showCategories(request, categoryService, imageService);
           categoryService.getCategory(request);

            return HOME_PAGE.getPath();
        } else {
            return LOGIN_PAGE.getPath();
        }

    }
}