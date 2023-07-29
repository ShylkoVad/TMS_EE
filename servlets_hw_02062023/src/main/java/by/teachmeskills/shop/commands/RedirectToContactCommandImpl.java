package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.shop.enums.PagesPathEnum.CONTACT_PAGE;

public class RedirectToContactCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return CONTACT_PAGE.getPath();
    }
}
