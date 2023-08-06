package by.teachmeskills.shop.utils;

import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.domain.Order;
import by.teachmeskills.shop.domain.Product;
import by.teachmeskills.shop.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CRUDUtils {

    private static final ConnectionPool connectionPool;
    private static int lastOrderId;
    private static final String GET_USER_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String GET_CATEGORIES_QUERY = "SELECT * FROM categories";
    private static final String GET_PRODUCTS_QUERY = "SELECT * FROM products WHERE categoryId = ?";
    private static final String GET_PRODUCT_ID_QUERY = "SELECT id, name, description, price, imagePath FROM products WHERE id = ?";
    private static final String ADD_USER_QUERY = "INSERT INTO users (id, email, password, name, surname, birthday, balance) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_ORDER_QUERY = "INSERT INTO orders (userId, createdAt, price) VALUES (?, ?, ?)";
    private static final String GET_ORDER_QUERY = "SELECT * FROM orders WHERE userId = ?";
    private static final String GET_PRODUCT_LIST_QUERY =
            "SELECT * FROM orders, product_list, products WHERE orders.id = product_list.orderId AND product_list.productId = products.id";
    private static final String ADD_PRODUCT_LIST_QUERY = "INSERT INTO product_list (orderId, productId) VALUES (?, ?)";
    private static final String GET_LAST_ORDER_ID_QUERY = "SELECT MAX(id) FROM orders";


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
                user = new User(resultSet.getInt(1),
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
            log.error(e.getMessage());
        }
        return product;
    }

    public static void saveUser(User user) {

        Connection connection = connectionPool.getConnection();
        try (PreparedStatement psInsert = connection.prepareStatement(ADD_USER_QUERY)) {
            psInsert.setInt(1, user.getId());
            psInsert.setString(2, user.getEmail());
            psInsert.setString(3, user.getPassword());
            psInsert.setString(4, user.getName());
            psInsert.setString(5, user.getSurname());
            psInsert.setString(6, user.getBirthday());
            psInsert.setDouble(7, user.getBalance());
            psInsert.execute();

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static void addOrder(Order order) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement psInsert = connection.prepareStatement(ADD_ORDER_QUERY)) {
            psInsert.setInt(1, order.getUserId());
            psInsert.setTimestamp(2, Timestamp.valueOf(order.getCreatedAt()));
            psInsert.setDouble(3, order.getPrice());

            addProductList(order);

            psInsert.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static void getLastOrderId() {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement psGet = connection.prepareStatement(GET_LAST_ORDER_ID_QUERY)) {
            ResultSet resultSet = psGet.executeQuery();
            while (resultSet.next()) {
                lastOrderId = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    private static void addProductList(Order order) {
        getLastOrderId();
        int orderId = ++lastOrderId;
        Connection connection = connectionPool.getConnection();
        List<Product> productList = order.getProductList();
        for (Product product : productList) {
            try (PreparedStatement psInsert = connection.prepareStatement(ADD_PRODUCT_LIST_QUERY)) {
                psInsert.setInt(1, orderId);
                psInsert.setInt(2, product.getId());
                psInsert.executeUpdate();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

    public static List<Order> getCustomerOrders(User user) {

        List<Order> orderList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_QUERY);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderList.add(new Order(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getTimestamp(3).toLocalDateTime(),
                        resultSet.getDouble(4)));
            }
            return orderList;
        } catch (SQLException e) {
            return orderList;
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
