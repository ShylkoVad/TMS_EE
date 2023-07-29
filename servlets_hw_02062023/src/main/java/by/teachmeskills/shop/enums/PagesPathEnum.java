package by.teachmeskills.shop.enums;

public enum PagesPathEnum {
    REGISTRATION_PAGE("registration.jsp"),
    LOGIN_PAGE("login.jsp"),
    HOME_PAGE("home.jsp"),
    CATEGORY_PAGE("category.jsp"),
    PRODUCT_PAGE("product.jsp"),
    SHOPPING_CART_PAGE("shoppingCart.jsp"),
    CONTACT_PAGE("contact.jsp");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
