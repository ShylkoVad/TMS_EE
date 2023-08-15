package by.teachmeskills.shop.repositories.impl;

import by.teachmeskills.shop.domain.Image;
import by.teachmeskills.shop.repositories.ImageRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImageRepositoryImpl implements ImageRepository {
    private static final String ADD_IMAGE_QUERY = "INSERT INTO images (imagePath, categoryId, productId, primaryImage) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_IMAGES_QUERY = "SELECT * FROM images";
    private static final String UPDATE_IMAGE_QUERY = "UPDATE images SET imagePath = ? WHERE id = ?";
    private static final String DELETE_IMAGE_QUERY = "DELETE FROM images WHERE id = ?";
    private static final String GET_IMAGE_BY_ID_QUERY = "SELECT * FROM images WHERE id = ?";
    private static final String GET_IMAGE_BY_CATEGORY_ID_QUERY = "SELECT * FROM images WHERE categoryId = ?";
    private static final String GET_IMAGES_BY_PRODUCT_ID_QUERY = "SELECT * FROM images WHERE productId = ?";

    @Override
    public Image create(Image entity) {
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_IMAGE_QUERY);

            preparedStatement.setString(1, entity.getImagePath());
            preparedStatement.setInt(2, entity.getCategoryId());
            preparedStatement.setInt(3, entity.getProductId());
            preparedStatement.setInt(4, entity.getPrimaryImage());
            preparedStatement.execute();

            connectionPool.closeConnection(connection);
            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public List<Image> read() {
        List<Image> images = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_IMAGES_QUERY);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                images.add(Image.builder()
                        .id(resultSet.getInt("id"))
                        .imagePath(resultSet.getString("imagePath"))
                        .categoryId(resultSet.getInt("categoryId"))
                        .productId(resultSet.getInt("productId"))
                        .primaryImage(resultSet.getInt("primaryImage"))
                        .build()
                );
            }

            resultSet.close();
            connectionPool.closeConnection(connection);
            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return images;
    }

    @Override
    public Image update(Image entity) {
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_IMAGE_QUERY);

            preparedStatement.setString(1, entity.getImagePath());
            preparedStatement.setInt(2, entity.getId());

            preparedStatement.execute();

            connectionPool.closeConnection(connection);
            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void delete(int id) {
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_IMAGE_QUERY);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            connectionPool.closeConnection(connection);
            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Image findById(int id) {
        Image image = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_IMAGE_BY_ID_QUERY);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                image = Image.builder()
                        .id(resultSet.getInt("id"))
                        .imagePath(resultSet.getString("imagePath"))
                        .categoryId(resultSet.getInt("categoryId"))
                        .productId(resultSet.getInt("productId"))
                        .primaryImage(resultSet.getInt("primaryImage"))
                        .build();
            }
            resultSet.close();

            connectionPool.closeConnection(connection);
            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return image;
    }

    @Override
    public Image findByCategoryId(int categoryId) {
        Image image = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_IMAGE_BY_CATEGORY_ID_QUERY);

            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                image = Image.builder()
                        .id(resultSet.getInt("id"))
                        .imagePath(resultSet.getString("imagePath"))
                        .categoryId(resultSet.getInt("categoryId"))
                        .build();
            }
            resultSet.close();

            connectionPool.closeConnection(connection);
            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return image;
    }

    @Override
    public List<Image> findByProductId(int productId) {
        List<Image> images = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_IMAGES_BY_PRODUCT_ID_QUERY);

            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                images.add(Image.builder()
                        .id(resultSet.getInt("id"))
                        .imagePath(resultSet.getString("imagePath"))
                        .categoryId(resultSet.getInt("categoryId"))
                        .productId(resultSet.getInt("productId"))
                        .primaryImage(resultSet.getInt("primaryImage"))
                        .build()
                );
            }
            resultSet.close();

            connectionPool.closeConnection(connection);
            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return images;
    }
}