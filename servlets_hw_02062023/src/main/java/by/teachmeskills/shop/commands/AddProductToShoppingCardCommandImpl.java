package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.domain.Cart;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.services.ImageService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ImageServiceImpl;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.shop.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.IMAGES;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;

public class AddProductToShoppingCardCommandImpl implements BaseCommand {
    private final ProductService productService = new ProductServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter(PRODUCT_ID.getValue()));

        HttpSession session = request.getSession();

        Cart shoppCart;
        Object sessionShoppCart = session.getAttribute(SHOPPING_CART.getValue());
        if (sessionShoppCart != null) {
            shoppCart = (Cart) sessionShoppCart;
        } else {
            shoppCart = new Cart();
            session.setAttribute(SHOPPING_CART.getValue(), shoppCart);
        }

        Product product = productService.getProductById(productId);
        shoppCart.addProduct(product);
        request.setAttribute(PRODUCT.getValue(), product);
        request.setAttribute(IMAGES.getValue(), imageService.getImagesByProductId(productId));

        return PRODUCT_PAGE.getPath();
    }
}