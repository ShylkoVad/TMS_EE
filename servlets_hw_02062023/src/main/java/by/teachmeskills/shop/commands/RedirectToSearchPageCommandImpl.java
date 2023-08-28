package by.teachmeskills.shop.commands;

import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.shop.enums.PagesPathEnum.SEARCH_PAGE;

public class RedirectToSearchPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest req) {
        return SEARCH_PAGE.getPath();
    }
}
