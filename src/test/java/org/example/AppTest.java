package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.TestCases.Test_Case_1_LoginAndNavigation_Test;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest extends Test_Case_1_LoginAndNavigation_Test {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        assertTrue(true);
        System.out.println("Run successfully.");

        Test_Case_1_LoginAndNavigation_Test test = new Test_Case_1_LoginAndNavigation_Test();

        test.setup();
        test.tearDown();
        test.loadProperties();
        test.initializeDriver();
        test.login();
        test.checkMyRequestsPageOpen();

        assertTrue(true);
        System.out.println("Run successfully again.");

    }
}
