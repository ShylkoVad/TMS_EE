package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class UpdateUserDataCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest req) throws CommandException {

        return PagesPathEnum.USER_ACCOUNT_PAGE.getPath();
    }
}
