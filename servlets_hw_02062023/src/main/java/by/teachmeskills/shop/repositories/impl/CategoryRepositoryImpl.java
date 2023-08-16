package by.teachmeskills.shop.repositories.impl;

import by.teachmeskills.shop.domain.Category;
import by.teachmeskills.shop.repositories.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private static final String ADD_CATEGORY_QUERY = "INSERT INTO categories (name) VALUES (?)";
    private static final String GET_ALL_CATEGORIES_QUERY = "SELECT * FROM categories";
    private static final String UPDATE_CATEGORY_QUERY = "UPDATE categories SET name = ? WHERE id = ?";
    private static final String DELETE_CATEGORY_QUERY = "DELETE FROM categories WHERE id = ?";
    private static final String GET_CATEGORY_BY_ID_QUERY = "SELECT * FROM categories WHERE id = ?";

    @Override
    public Category create(Category entity) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CATEGORY_QUERY);

            preparedStatement.setString(1, entity.getName());
            preparedStatement.execute();

            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public List<Category> read() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CATEGORIES_QUERY);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build()
                );
            }
            resultSet.close();

            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return categories;
    }

    @Override
    public Category update(Category entity) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_QUERY);

            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.execute();

            preparedStatement.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement psDelete = connection.prepareStatement(DELETE_CATEGORY_QUERY);

            psDelete.setInt(1, id);
            psDelete.execute();

            psDelete.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Category findById(int id) {
        Category category = null;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement psGet = connection.prepareStatement(GET_CATEGORY_BY_ID_QUERY);

            psGet.setInt(1, id);
            ResultSet resultSet = psGet.executeQuery();
            while (resultSet.next()) {
                category = Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();
            }
            resultSet.close();

            psGet.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return category;
    }
}