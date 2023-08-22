package by.teachmeskills.shop.enums;

import lombok.Getter;

@Getter
public enum RequestParamsEnum {
    COMMAND("command"),
    NAME("name"),
    SURNAME("surname"),
    BIRTHDAY("birthday"),
    EMAIL("email"),
    LOGIN("email"),
    PASSWORD("password"),
    USER("user"),
    INFO("info"),
    IMAGES("images"),
    CATEGORIES("categories"),
    CATEGORY_ID("category_id"),
    PRODUCTS("products"),
    PRODUCT("product"),
    PRODUCT_ID("product_id"),
    SHOPPING_CART("shopping_cart"),
    SHOPPING_CART_PRODUCTS("cartProductsList"),
    ORDERS("orders");


    private final String value;

    RequestParamsEnum(String value) {
        this.value = value;
    }
}