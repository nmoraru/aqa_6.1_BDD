package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Data;

import java.util.Locale;

@Data
public class DataGenerator {

    public static AuthInfo generateValidUser() {
        AuthInfo validUser = new AuthInfo(
                "vasya",
                "qwerty123");
        return validUser;
    }

    public static AuthInfo generateInvalidUser() {
        Faker faker = new Faker(new Locale("en"));
        AuthInfo invalidUser = new AuthInfo(
                faker.name().firstName().toLowerCase(),
                faker.internet().password());
        return invalidUser;
    }

    public static VerificationInfo generateValidVerificationCode() {
        return new VerificationInfo("12345");
    }

    public static VerificationInfo generateInvalidVerificationCode() {
        return new VerificationInfo("123");
    }

}
