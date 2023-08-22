package by.teachmeskills.shop.services;

import by.teachmeskills.shop.domain.Category;
import jakarta.servlet.http.HttpServletRequest;

public interface CategoryService extends BaseService<Category> {
    Category getCategoryById(int id);

    void getCategory(HttpServletRequest request);

}
