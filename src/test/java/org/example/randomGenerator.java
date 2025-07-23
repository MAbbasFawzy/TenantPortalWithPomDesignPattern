package org.example;

import java.util.Random;

public class randomGenerator {

	private static final String[] FIRST_NAMES = {"Liam", "Emma", "Noah", "Olivia", "Ava", "Isabella", "Sophia", "Mason"};
	private static final String[] LAST_NAMES = {"Smithson", "Johnson", "Williams", "Jones", "Browner", "Paisley", "Miller", "Maisonette"};
	private static final String[] EMAIL_DOMAINS = {"example.com", "test.com", "demo.com", "sample.com", "mail.com", "webmail.com", "contact.com"};
	private static final String[] NUMBERS = {
            "1234567890", "6543217890", "7890127890", "3456787890", "9012347890", "5678907890", "2345677890", "8901237890", "4567897890", "0123457890",
            "1234567891", "6543217891", "7890127891", "3456787891", "9012347891", "5678907891", "2345677891", "8901237891", "4567897891", "0123457891",
            "1234567892", "6543217892", "7890127892", "3456787892", "9012347892", "5678907892", "2345677892", "8901237892", "4567897892", "0123457892",
            "1234567893", "6543217893", "7890127893", "3456787893", "9012347893", "5678907893", "2345677893", "8901237893", "4567897893", "0123457893",
            "1234567894", "6543217894", "7890127894", "3456787894", "9012347894", "5678907894", "2345677894", "8901237894", "4567897894", "0123457894",
            "1234567895", "6543217895", "7890127895", "3456787895", "9012347895", "5678907895", "2345677895", "8901237895", "4567897895", "0123457895",
            "1234567896", "6543217896", "7890127896", "3456787896", "9012347896", "5678907896", "2345677896", "8901237896", "4567897896", "0123457896",
            "1234567897", "6543217897", "7890127897", "3456787897", "9012347897", "5678907897", "2345677897", "8901237897", "4567897897", "0123457897",
            "1234567898", "6543217898", "7890127898", "3456787898", "9012347898", "5678907898", "2345677898", "8901237898", "4567897898", "0123457898",
            "1234567899", "6543217899", "7890127899", "3456787899", "9012347899", "5678907899", "2345677899", "8901237899", "4567897899", "0123457899"
    };
    private static final String[] NUMBERSSAUDI = {
            "1234", "6543", "7890", "3456", "9012", "5678", "2345", "8901", "4567", "0123",
            "6789", "2348", "3450", "4561", "5672", "6783", "7894", "8905",
            "1230", "6541", "7892", "3453", "9014", "5675", "2346", "8907", "4568", "0129",
            "6780", "2341", "3452", "4563", "5674", "6785", "7896", "8907",
            "1231", "6542", "7893", "3454", "9015", "5676", "2347", "8908", "4569", "0120",
            "6781", "2342", "3453", "4564", "5675", "6786", "7897", "8908",
            "1232", "6543", "7894", "3455", "9016", "5677", "2348", "8909", "4560", "0121",
            "6782", "2343", "3454", "4565", "5676", "6787", "7898", "8909",
            "1233", "6544", "7895", "3456", "9017", "5678", "2349", "8900", "4561", "0122",
            "6783", "2344", "3455", "4566", "5677", "6788", "7899", "8900",
            "1235", "6545", "7896", "3457", "9018", "5679", "2340", "8901", "4562", "0123",
            "6784", "2345", "3456", "4567", "5678", "6789", "7890", "8901"
    };

    public static class Visitor {
        public String firstName;
        public String lastName;
        public String email;
        public String numbers;
        public String numbersSaudi;

        public Visitor(String firstName, String lastName, String email, String numbers, String numbersSaudi) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.numbers = numbers;
            this.numbersSaudi = numbersSaudi;

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
        String numbersSaudi = NUMBERSSAUDI[random.nextInt(NUMBERSSAUDI.length)];
        String phoneNumberSuffix = String.valueOf(random.nextInt(1000000));
        String phoneNumber = numbers + "-" + phoneNumberSuffix;

        // Return the generated contact information
        return new Visitor(firstName, lastName, email, numbers, numbersSaudi);
    }
}
