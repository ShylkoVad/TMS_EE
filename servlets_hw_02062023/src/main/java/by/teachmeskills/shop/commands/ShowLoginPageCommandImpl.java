package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.shop.enums.PagesPathEnum.LOGIN_PAGE;

public class ShowLoginPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        return LOGIN_PAGE.getPath();
    }
}