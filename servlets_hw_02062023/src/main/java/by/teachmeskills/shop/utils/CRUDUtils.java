package by.teachmeskills.shop.utils;

import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    private static final ConnectionPool connectionPool;

    private static final String GET_USER_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String GET_CATEGORIES_QUERY = "SELECT * FROM categories";
    private static final String GET_PRODUCTS_QUERY = "SELECT * FROM products WHERE categoryId = ?";
    private static final String GET_PRODUCT_ID_QUERY = "SELECT id, name, description, price, imagePath FROM products WHERE id = ?";
    private static final String ADD_USER_QUERY = "INSERT INTO users (id, email, password, name, surname, birthday, balance) VALUES (?, ?, ?, ?, ?, ?, ?)";


    private CRUDUtils() {
    }

    static {
        connectionPool = ConnectionPool.getInstance();
    }

    public static User getUser(String email) {
        User user = null;
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_QUERY);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString(1),
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

    public static List<Category> getAllCategories() {
        List<Category> categoryArrayList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_CATEGORIES_QUERY);
            while (resultSet.next()) {
                categoryArrayList.add(new Category(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
            return categoryArrayList;
        } catch (SQLException e) {
            return categoryArrayList;
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    public static List<Product> getCategoryProducts(String categoryId) {
        List<Product> productList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_QUERY);
            preparedStatement.setInt(1, Integer.parseInt(categoryId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)));
            }
            return productList;
        } catch (SQLException e) {
            return productList;
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    public static Product getProductById(String productId) {
        Product product = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_ID_QUERY)) {
            preparedStatement.setInt(1, Integer.parseInt(productId));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product = Product.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .price(resultSet.getDouble(4))
                        .imagePath(resultSet.getString(5))
                        .build();
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
