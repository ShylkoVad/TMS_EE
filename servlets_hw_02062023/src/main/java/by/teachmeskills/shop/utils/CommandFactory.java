package by.teachmeskills.shop.utils;

import by.teachmeskills.shop.commands.AddProductToShoppingCardCommandImpl;
import by.teachmeskills.shop.commands.BaseCommand;
import by.teachmeskills.shop.commands.CheckoutCommandImpl;
import by.teachmeskills.shop.commands.LoginCommandImpl;
import by.teachmeskills.shop.commands.RedirectToAccountUserCommandImpl;
import by.teachmeskills.shop.commands.RedirectToCategoryPageImpl;
import by.teachmeskills.shop.commands.RedirectToContactCommandImpl;
import by.teachmeskills.shop.commands.RedirectToHomePageCommandIml;
import by.teachmeskills.shop.commands.RedirectToProductCommandImpl;
import by.teachmeskills.shop.commands.RedirectToRegistrationCommandImpl;
import by.teachmeskills.shop.commands.RedirectToSearchPageCommandImpl;
import by.teachmeskills.shop.commands.RedirectToShoppingCartCommandImpl;
import by.teachmeskills.shop.commands.RegistrationUserCommandImpl;
import by.teachmeskills.shop.commands.RemoveProductFromShoppingCartCommandImpl;
import by.teachmeskills.shop.commands.SearchCommandImpl;
import by.teachmeskills.shop.commands.ShowLoginPageCommandImpl;
import by.teachmeskills.shop.commands.UpdateUserDataCommandImpl;
import by.teachmeskills.shop.enums.CommandsEnum;
import by.teachmeskills.shop.enums.RequestParamsEnum;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private CommandFactory() {
    }

    private static final Map<String, BaseCommand> COMMAND_LIST = new HashMap<>();

    static {
        COMMAND_LIST.put(CommandsEnum.SHOW_LOGIN_COMMAND.getCommand(), new ShowLoginPageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_TO_REGISTRATION_COMMAND.getCommand(), new RedirectToRegistrationCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_TO_CONTACT_COMMAND.getCommand(), new RedirectToContactCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_TO_CART_COMMAND.getCommand(), new RedirectToShoppingCartCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_TO_HOME_PAGE_COMMAND.getCommand(), new RedirectToHomePageCommandIml());
        COMMAND_LIST.put(CommandsEnum.ADD_PRODUCT_TO_SHOPPING_CART.getCommand(), new AddProductToShoppingCardCommandImpl());
        COMMAND_LIST.put(CommandsEnum.DELETE_PRODUCT_FROM_CART.getCommand(), new RemoveProductFromShoppingCartCommandImpl());
        COMMAND_LIST.put(CommandsEnum.LOGIN_COMMAND.getCommand(), new LoginCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REGISTRATION_COMMAND.getCommand(), new RegistrationUserCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_TO_CATEGORY_COMMAND.getCommand(), new RedirectToCategoryPageImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_TO_PRODUCT_COMMAND.getCommand(), new RedirectToProductCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_TO_USER_ACCOUNT_PAGE_COMMAND.getCommand(), new RedirectToAccountUserCommandImpl());
        COMMAND_LIST.put(CommandsEnum.CHECKOUT_COMMAND.getCommand(), new CheckoutCommandImpl());
        COMMAND_LIST.put(CommandsEnum.UPDATE_USER_DATA_COMMAND.getCommand(), new UpdateUserDataCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_TO_SEARCH_PAGE_COMMAND.getCommand(), new RedirectToSearchPageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.SEARCH_COMMAND.getCommand(), new SearchCommandImpl());
    }

    public static BaseCommand defineCommand(HttpServletRequest request) {
        String commandKey = request.getParameter(RequestParamsEnum.COMMAND.getValue());
        if (commandKey == null || commandKey.isEmpty()) {
            commandKey = CommandsEnum.SHOW_LOGIN_COMMAND.getCommand();
        }
        return COMMAND_LIST.get(commandKey);
    }
}
