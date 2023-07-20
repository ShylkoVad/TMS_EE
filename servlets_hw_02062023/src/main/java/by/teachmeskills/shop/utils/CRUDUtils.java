package by.teachmeskills.shop.utils;

import by.teachmeskills.shop.model.Category;
import by.teachmeskills.shop.model.Product;
import by.teachmeskills.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    private static final ConnectionPool connectionPool;
    private static final String GET_USER_QUERY = "SELECT * FROM users WHERE email = ? AND password = ?";
    private static final String GET_CATEGORIES_QUERY = "SELECT * FROM categories";
    private static final String GET_PRODUCTS_QUERY = "SELECT * FROM products WHERE categoryId = ?";
    private static final String GET_PRODUCT_QUERY = "SELECT name, description, price, imagePath FROM products WHERE id = ?";
    private static final String ADD_USER_QUERY = "INSERT INTO users (id, email, password, name, surname, birthday, balance) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private CRUDUtils() {
    }

    static {
        connectionPool = ConnectionPool.getInstance();
    }

    public static User getUser(String email, String password) {
        User user = null;
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_QUERY);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getBigDecimal(7).doubleValue());
            }
            return user;
        } catch (SQLException e) {
            return null;
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    public static List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement psGet = connection.prepareStatement(GET_CATEGORIES_QUERY)) {
            ResultSet resultSet = psGet.executeQuery();

            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public static List<Product> getCategoryProducts(String categoryId) {
        List<Product> products = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement psGet = connection.prepareStatement(GET_PRODUCTS_QUERY)) {
            psGet.setInt(1, Integer.parseInt(categoryId));
            ResultSet resultSet = psGet.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public static Product getProductById(String productId) {
        Product product = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement psGet = connection.prepareStatement(GET_PRODUCT_QUERY)) {
            psGet.setInt(1, Integer.parseInt(productId));
            ResultSet resultSet = psGet.executeQuery();

            while (resultSet.next()) {
                product = new Product(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4)
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    public static void saveUser(User user) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement psInsert = connection.prepareStatement(ADD_USER_QUERY)) {
            psInsert.setString(1, user.getId());
            psInsert.setString(2, user.getEmail());
            psInsert.setString(3, user.getPassword());
            psInsert.setString(4, user.getName());
            psInsert.setString(5, user.getSurname());
            psInsert.setString(6, user.getBirthday());
            psInsert.setDouble(7, user.getBalance());
            psInsert.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
