package by.teachmeskills.shop.model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private String id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private double balance;

    public User(String login, String password, String name, String surname) {
        id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        balance = 0;
    }
    public User() {
        this.login = "empty";
        this.password = "empty";
    }
    public User(String id, String login, String password, String name, String surname, double balance) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}