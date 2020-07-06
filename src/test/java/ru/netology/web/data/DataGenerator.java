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

    public static TransactionInfo generateTransactionUnderBalanceAnotherCards() {
        return new TransactionInfo("5000", "5559 0000 0000 0002");
    }

    public static TransactionInfo generateTransactionUnderBalanceAnotherCardsRollback() {
        return new TransactionInfo("5000", "5559 0000 0000 0001");
    }

    public static TransactionInfo generateTransactionAllBalanceAnotherCards() {
        return new TransactionInfo("10000", "5559 0000 0000 0002");
    }

    public static TransactionInfo generateTransactionAllBalanceAnotherCardsRollback() {
        return new TransactionInfo("10000", "5559 0000 0000 0001");
    }

    public static TransactionInfo generateTransactionOverBalanceAnotherCards() {
        return new TransactionInfo("20000", "5559 0000 0000 0002");
    }

    public static TransactionInfo generateTransactionWithCentAnotherCards() {
        return new TransactionInfo("0,01", "5559 0000 0000 0001");
    }

    public static TransactionInfo generateTransactionWithCentAnotherCardsRollback() {
        return new TransactionInfo("0,01", "5559 0000 0000 0001");
    }



}
