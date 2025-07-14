package org.example;

import java.util.Random;

public class randomGenerator {

	private static final String[] FIRST_NAMES = {"Liam", "Emma", "Noah", "Olivia", "Ava", "Isabella", "Sophia", "Mason"};
	private static final String[] LAST_NAMES = {"Smithson", "Johnson", "Williams", "Jones", "Browner", "Paisley", "Miller", "Maisonette"};
	private static final String[] EMAIL_DOMAINS = {"example.com", "test.com", "demo.com", "sample.com", "mail.com", "webmail.com", "contact.com"};
	private static final String[] NUMBERS = {"1234567890", "6543217890", "7890127890", "3456787890", "9012347890", "5678907890", "2345677890", "8901237890", "4567897890", "0123457890", "6789017890", "2348907890", "3450127890", "4561237890", "5672347890", "6783457890", "7894567890", "8905677890"};

    public static class Visitor {
        public String firstName;
        public String lastName;
        public String email;
        public String numbers;

        public Visitor(String firstName, String lastName, String email, String numbers) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.numbers = numbers;
        }
    }

    public static Visitor generateRandomContact() {
        Random random = new Random();

        // Generate random first name
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];

        // Generate random last name
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        // Generate random email domain
        String emailDomain = EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)];

        // Generate random email username (with numbers)
        String emailUsername = firstName.toLowerCase() + lastName.toLowerCase() + random.nextInt(100);

        // Generate random email
        String email = emailUsername + "@" + emailDomain;

        // Generate random phone number
        String numbers = NUMBERS[random.nextInt(NUMBERS.length)];
        String phoneNumberSuffix = String.valueOf(random.nextInt(1000000));
        String phoneNumber = numbers + "-" + phoneNumberSuffix;

        // Return the generated contact information
        return new Visitor(firstName, lastName, email, numbers);
    }
}
