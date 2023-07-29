package by.teachmeskills.shop.commands;

import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.shop.enums.PagesPathEnum.REGISTRATION_PAGE;

public class RedirectToRegistrationCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return REGISTRATION_PAGE.getPath();
    }
}
