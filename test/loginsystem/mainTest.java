/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
// Unit tests for the login system
package loginsystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


      // This class has tests for the login class.
      // Each test checks if a method works the way it should.
      // There are 14 tests in total:
      // 6 tests use assertEquals
      // 8 tests use assertTrue or assertFalse
public class mainTest {
    
    public mainTest() {
    }
    // Runs once before all the tests.
      @BeforeClass
    public static void setUpClass() {
    }
    
    // Runs once after all the tests.
    @AfterClass
    public static void tearDownClass() {
    }
    
    // Runs before each test.
    // Clears the saved user info so tests don't mess each other up.
    @Before
    public void setUp() {
        login.savedUsername = "";
        login.savedPassword = "";
        login.savedFirstName = "";
        login.savedLastName = "";
        login.savedPhone = "";
    }
    
    // Runs after each test.
    @After
    public void tearDown() {
    }

    // ===================================================
    // assertEquals TESTS (6 tests)
    // ===================================================

    // TEST 1: Username is correctly formatted
    // Test data: "kyl_1"
    // Expected: true
    @Test
    public void testUsernameCorrectlyFormatted() {
        boolean result = login.checkUserName("kyl_1");
        assertEquals(true, result);
        System.out.println("Username is correctly formatted: true");
    }

    // TEST 2: Username is incorrectly formatted
    // Test data: "kyle!!!!!!!"
    // Expected: false
    @Test
    public void testUsernameIncorrectlyFormatted() {
        boolean result = login.checkUserName("kyle!!!!!!!");
        assertEquals(false, result);
        System.out.println("Username is incorrectly formatted: false");
    }

    // TEST 3: Password meets complexity
    // Test data: "Ch&sec@ke99!"
    // Expected: true
    @Test
    public void testPasswordMeetsComplexity() {
        boolean result = login.checkPasswordComplexity("Ch&sec@ke99!");
        assertEquals(true, result);
        System.out.println("Password successfully captured.");
    }

    // TEST 4: Password does not meet complexity
    // Test data: "password"
    // Expected: false
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        boolean result = login.checkPasswordComplexity("password");
        assertEquals(false, result);
        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
    }

    // TEST 5: Cell phone is correctly formatted
    // Test data: "+27838968976"
    // Expected: true
    @Test
    public void testPhoneCorrectlyFormatted() {
        boolean result = login.checkCellPhoneNumber("+27838968976");
        assertEquals(true, result);
        System.out.println("Cell number successfully captured.");
    }

    // TEST 6: Cell phone is incorrectly formatted
    // Test data: "08966553"
    // Expected: false
    @Test
    public void testPhoneIncorrectlyFormatted() {
        boolean result = login.checkCellPhoneNumber("08966553");
        assertEquals(false, result);
        System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
    }

    // ===================================================
    // assertTrue / assertFalse TESTS (8 tests)
    // ===================================================

    // TEST 7: Login Successful
    // Expected: True
    @Test
    public void testLoginSuccessful() {
        login.savedUsername = "kyl_1";
        login.savedPassword = "Ch&sec@ke99!";
        login.savedFirstName = "Kyle";
        login.savedLastName = "Smith";
        
        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
        System.out.println("Login Successful: True");
    }

    // TEST 8: Login Failed
    // Expected: False
    @Test
    public void testLoginFailed() {
        login.savedUsername = "kyl_1";
        login.savedPassword = "Ch&sec@ke99!";
        
        assertFalse(login.loginUser("wrong", "wrong"));
        System.out.println("Login Failed: False");
    }

    // TEST 9: Username correctly formatted
    // Expected: True
    @Test
    public void testUsernameCorrectlyFormattedTrue() {
        assertTrue(login.checkUserName("kyl_1"));
        System.out.println("Username correctly formatted: True");
    }

    // TEST 10: Username incorrectly formatted
    // Expected: False
    @Test
    public void testUsernameIncorrectlyFormattedFalse() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
        System.out.println("Username incorrectly formatted: False");
    }

    // TEST 11: Password meets complexity
    // Expected: True
    @Test
    public void testPasswordMeetsComplexityTrue() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
        System.out.println("Password meets complexity: True");
    }

    // TEST 12: Password does not meet complexity
    // Expected: False
    @Test
    public void testPasswordDoesNotMeetComplexityFalse() {
        assertFalse(login.checkPasswordComplexity("password"));
        System.out.println("Password does not meet complexity: False");
    }

    // TEST 13: Cell phone correctly formatted
    // Expected: True
    @Test
    public void testCellPhoneCorrectlyFormattedTrue() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        System.out.println("Cell phone number correctly formatted: True");
    }

    // TEST 14: Cell phone incorrectly formatted
    // Expected: False
    @Test
    public void testCellPhoneIncorrectlyFormattedFalse() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
        System.out.println("Cell phone number incorrectly formatted: False");
    }
}
