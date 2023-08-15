package by.teachmeskills.shop.services;

import by.teachmeskills.shop.domain.User;

public interface UserService extends BaseService<User> {
    User getUserById(int id);

    User getUserByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);
}