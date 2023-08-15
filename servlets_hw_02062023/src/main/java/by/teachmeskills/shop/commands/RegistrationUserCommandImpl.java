package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.domain.User;
import by.teachmeskills.shop.exceptions.UserExistsException;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.UserService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.ImageServiceImpl;
import by.teachmeskills.shop.services.impl.UserServiceImpl;
import by.teachmeskills.shop.utils.ValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.teachmeskills.shop.enums.PagesPathEnum.HOME_PAGE;
import static by.teachmeskills.shop.enums.PagesPathEnum.REGISTRATION_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.BIRTHDAY;
import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORIES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.EMAIL;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.NAME;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PASSWORD;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SURNAME;

public class RegistrationUserCommandImpl implements BaseCommand {
    private final UserService userService = new UserServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {

        String email = request.getParameter(EMAIL.getValue());
        String name = request.getParameter(NAME.getValue());
        String surname = request.getParameter(SURNAME.getValue());
        String password = request.getParameter(PASSWORD.getValue());
        String birthday = request.getParameter(BIRTHDAY.getValue());
        try {
            ValidatorUtils.validateRegistration(email, name, surname, password, birthday);
            checkUser(email);
        } catch (UserExistsException e) {
            String varInfo = e.getMessage();
            request.setAttribute("info", varInfo);
            return REGISTRATION_PAGE.getPath();
        }
        User user = User.builder()
                .email(email)
                .password(password)
                .name(name)
                .surname(surname)
                .birthday(LocalDate.parse(birthday))
                .build();

        userService.create(user);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        List<Category> categories = categoryService.read();
        List<Image> images = new ArrayList<>();
        for (Category category : categories) {
            images.add(imageService.getImageByCategoryId(category.getId()));
        }
        request.setAttribute(CATEGORIES.getValue(), categories);
        request.setAttribute(IMAGES.getValue(), images);

        return HOME_PAGE.getPath();
    }

    private void checkUser(String email) throws UserExistsException {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            throw new UserExistsException("Пользователь с email - " + email + " уже зарегистрирован." +
                    " Перейдите на страницу входа");
        }
    }
}
