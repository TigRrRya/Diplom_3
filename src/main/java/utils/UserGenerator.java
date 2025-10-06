package utils;

import com.github.javafaker.Faker;
import stellarburgers.model.User;
import stellarburgers.model.UserLogin;

import java.util.Locale;

public class UserGenerator {

    private static final Faker FAKER = new Faker(new Locale("en"));


    public static User getValidUser() {
        String email = FAKER.internet().emailAddress();
        String password = FAKER.internet().password(8, 12);
        String name = FAKER.name().firstName();
        return new User(email, password, name);
    }


    public static UserLogin getCredentials(User user) {
        return new UserLogin(user.getEmail(), user.getPassword());
    }

    public static User getUserWithCustomPassword(String password) {
        String email = FAKER.internet().emailAddress();
        String name = FAKER.name().firstName();
        return new User(email, password, name);
    }

}
