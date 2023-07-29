package by.teachmeskills.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
}
