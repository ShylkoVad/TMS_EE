package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.domain.Image;
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

import java.util.ArrayList;
import java.util.List;

import static by.teachmeskills.shop.enums.PagesPathEnum.HOME_PAGE;
import static by.teachmeskills.shop.enums.PagesPathEnum.LOGIN_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORIES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.LOGIN;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PASSWORD;
import static by.teachmeskills.shop.enums.RequestParamsEnum.USER;

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

            List<Category> categories = categoryService.read();
            List<Image> images = new ArrayList<>();
            for (Category category : categories) {
                images.add(imageService.getImageByCategoryId(category.getId()));
            }
            request.setAttribute(CATEGORIES.getValue(), categories);
            request.setAttribute(IMAGES.getValue(), images);
            return HOME_PAGE.getPath();
        } else {
            return LOGIN_PAGE.getPath();
        }

    }
}