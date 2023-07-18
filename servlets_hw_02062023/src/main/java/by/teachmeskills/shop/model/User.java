package by.teachmeskills.shop.model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private String id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String birthday;
    private double balance;

    public User(String email, String password, String name, String surname, String birthday) {
        id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        balance = 0;
    }


    public User() {
        this.email = "empty";
        this.password = "empty";
    }

    public User(String id, String email, String password, String name, String surname, String birthday, double balance) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.balance = balance;
    }


    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}