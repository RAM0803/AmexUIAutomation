package utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class TestDataGenerator {
    private static final Faker faker = new Faker(new Locale("fr-FR"));

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getDOB(int minAge, int maxAge) {
        Faker faker = new Faker();
        Date birthday = faker.date().birthday(minAge, maxAge);

        LocalDate localDate = birthday.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

    public static String getRandomPhoneNumber() {
        return "9" + faker.number().digits(9);
    }

    public static String getRandomAddress() {
        return faker.address().streetAddress();
    }

    public static String getRandomCity() {
        return faker.address().city();
    }

    public static String getRandomPostalCode() {
        return faker.address().zipCode();
    }
}