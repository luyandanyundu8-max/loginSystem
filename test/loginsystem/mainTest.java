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
        Login.savedUsername = "";
        Login.savedPassword = "";
        Login.savedFirstName = "";
        Login.savedLastName = "";
        Login.savedPhone = "";
        Message.resetAll();
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
        boolean result = Login.checkUserName("kyl_1");
        assertEquals(true, result);
        System.out.println("Username is correctly formatted: true");
    }

    // TEST 2: Username is incorrectly formatted
    // Test data: "kyle!!!!!!!"
    // Expected: false
    @Test
    public void testUsernameIncorrectlyFormatted() {
        boolean result = Login.checkUserName("kyle!!!!!!!");
        assertEquals(false, result);
        System.out.println("Username is incorrectly formatted: false");
    }

    // TEST 3: Password meets complexity
    // Test data: "Ch&sec@ke99!"
    // Expected: true
    @Test
    public void testPasswordMeetsComplexity() {
        boolean result = Login.checkPasswordComplexity("Ch&sec@ke99!");
        assertEquals(true, result);
        System.out.println("Password successfully captured.");
    }

    // TEST 4: Password does not meet complexity
    // Test data: "password"
    // Expected: false
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        boolean result = Login.checkPasswordComplexity("password");
        assertEquals(false, result);
        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
    }

    // TEST 5: Cell phone is correctly formatted
    // Test data: "+27838968976"
    // Expected: true
    @Test
    public void testPhoneCorrectlyFormatted() {
        boolean result = Login.checkCellPhoneNumber("+27838968976");
        assertEquals(true, result);
        System.out.println("Cell number successfully captured.");
    }

    // TEST 6: Cell phone is incorrectly formatted
    // Test data: "08966553"
    // Expected: false
    @Test
    public void testPhoneIncorrectlyFormatted() {
        boolean result = Login.checkCellPhoneNumber("08966553");
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
        Login.savedUsername = "kyl_1";
        Login.savedPassword = "Ch&sec@ke99!";
        Login.savedFirstName = "Kyle";
        Login.savedLastName = "Smith";
        
        assertTrue(Login.loginUser("kyl_1", "Ch&sec@ke99!"));
        System.out.println("Login Successful: True");
    }

    // TEST 8: Login Failed
    // Expected: False
    @Test
    public void testLoginFailed() {
        Login.savedUsername = "kyl_1";
        Login.savedPassword = "Ch&sec@ke99!";
        
        assertFalse(Login.loginUser("wrong", "wrong"));
        System.out.println("Login Failed: False");
    }

    // TEST 9: Username correctly formatted
    // Expected: True
    @Test
    public void testUsernameCorrectlyFormattedTrue() {
        assertTrue(Login.checkUserName("kyl_1"));
        System.out.println("Username correctly formatted: True");
    }

    // TEST 10: Username incorrectly formatted
    // Expected: False
    @Test
    public void testUsernameIncorrectlyFormattedFalse() {
        assertFalse(Login.checkUserName("kyle!!!!!!!"));
        System.out.println("Username incorrectly formatted: False");
    }

    // TEST 11: Password meets complexity
    // Expected: True
    @Test
    public void testPasswordMeetsComplexityTrue() {
        assertTrue(Login.checkPasswordComplexity("Ch&sec@ke99!"));
        System.out.println("Password meets complexity: True");
    }

    // TEST 12: Password does not meet complexity
    // Expected: False
    @Test
    public void testPasswordDoesNotMeetComplexityFalse() {
        assertFalse(Login.checkPasswordComplexity("password"));
        System.out.println("Password does not meet complexity: False");
    }

    // TEST 13: Cell phone correctly formatted
    // Expected: True
    @Test
    public void testCellPhoneCorrectlyFormattedTrue() {
        assertTrue(Login.checkCellPhoneNumber("+27838968976"));
        System.out.println("Cell phone number correctly formatted: True");
    }

    // TEST 14: Cell phone incorrectly formatted
    // Expected: False
    @Test
    public void testCellPhoneIncorrectlyFormattedFalse() {
        assertFalse(Login.checkCellPhoneNumber("08966553"));
        System.out.println("Cell phone number incorrectly formatted: False");
    }
     
    // ===================================================
    // PART 2 TESTS — Message class (12 tests)
    // ===================================================

    // TEST 15: Message ID is created and is 10 digits
    @Test
    public void testMessageIDCreated() {
        String id = Message.generateMessageID();
        System.out.println("Message ID generated: " + id);
        assertEquals(10, id.length());
    }

    // TEST 16: Message ID valid (<= 10 chars)
    @Test
    public void testCheckMessageIDValid() {
        Message m = new Message("1234567890", 1, "+27718693002",
                                "Hi Mike, can you join us for dinner tonight?");
        assertEquals(true, m.checkMessageID());
        System.out.println("Message ID valid");
    }

    // TEST 17: Message ID invalid (> 10 chars)
    @Test
    public void testCheckMessageIDInvalid() {
        Message m = new Message("12345678901", 1, "+27718693002",
                                "Hi Mike, can you join us for dinner tonight?");
        assertEquals(false, m.checkMessageID());
        System.out.println("Message ID invalid");
    }

    // TEST 18: Recipient cell correctly formatted
    @Test
    public void testRecipientCellSuccess() {
        Message m = new Message("1234567890", 1, "+27718693002",
                                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.",
                     m.checkRecipientCell());
        System.out.println("Recipient correct");
    }

    // TEST 19: Recipient cell incorrectly formatted
    @Test
    public void testRecipientCellFailure() {
        Message m = new Message("1234567890", 1, "08575975889",
                                "Hi Keegan, did you receive the payment?");
        assertEquals(
            "Cell phone number is incorrectly formatted or does "
            + "not contain an international code. Please correct "
            + "the number and try again.",
            m.checkRecipientCell());
        System.out.println("Recipient incorrect");
    }

    // TEST 20: Message length success (<= 250 chars)
    @Test
    public void testMessageLengthSuccess() {
        Message m = new Message("1234567890", 1, "+27718693002",
                                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", m.checkMessageLength());
        System.out.println("Message length OK");
    }

    // TEST 21: Message length failure (> 250 chars)
    @Test
    public void testMessageLengthFailure() {
        String longMsg = "";
        for (int i = 0; i < 260; i++) {
            longMsg = longMsg + "a";
        }
        Message m = new Message("1234567890", 1, "+27718693002", longMsg);
        assertEquals(
            "Message exceeds 250 characters by 10; please reduce the size.",
            m.checkMessageLength());
        System.out.println("Message too long");
    }

    // TEST 22: Message hash correct (rubric test case 1)
    @Test
    public void testMessageHash() {
        Message m = new Message("0012345678", 0, "+27718693002",
                                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", m.getMessageHash());
        System.out.println("Hash correct");
    }

    // TEST 23: SentMessage — Send
    @Test
    public void testSentMessageSend() {
        Message m = new Message("1234567890", 1, "+27718693002",
                                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", m.SentMessage("1"));
        assertEquals(1, Message.returnTotalMessages());
        System.out.println("Message sent");
    }

    // TEST 24: SentMessage — Disregard
    @Test
    public void testSentMessageDisregard() {
        Message m = new Message("1234567890", 1, "+27718693002",
                                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Press 0 to delete the message.", m.SentMessage("2"));
        assertEquals(0, Message.returnTotalMessages());
        System.out.println("Message disregarded");
    }

    // TEST 25: SentMessage — Store
    @Test
    public void testSentMessageStore() {
        Message m = new Message("1234567890", 1, "+27718693002",
                                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored.", m.SentMessage("3"));
        assertEquals(0, Message.returnTotalMessages());
        System.out.println("Message stored");
    }

    // TEST 26: Two messages — one Send, one Disregard
    @Test
    public void testTwoMessagesSendAndDisregard() {
        Message m1 = new Message("1111111111", 1, "+27718693002",
                                 "Hi Mike, can you join us for dinner tonight?");
        Message m2 = new Message("2222222222", 2, "08575975889",
                                 "Hi Keegan, did you receive the payment?");

        assertEquals("Message successfully sent.", m1.SentMessage("1"));
        assertEquals("Press 0 to delete the message.", m2.SentMessage("2"));

        assertEquals(1, Message.returnTotalMessages());
        System.out.println("Send + Disregard flow correct");
    }
    // TEST 27: printMessageDetails returns the exact format
@Test
public void testPrintMessageDetails() {
    Message m = new Message("1234567890", 1, "+27718693002", "Hi Mike");
    String expected = "Message ID: 1234567890"
                    + "\nMessage Hash: " + m.getMessageHash()
                    + "\nRecipient: +27718693002"
                    + "\nMessage: Hi Mike";
    assertEquals(expected, m.printMessageDetails());
}

// TEST 28: printMessages returns empty string when nothing sent
@Test
public void testPrintMessagesEmpty() {
    assertEquals("", Message.printMessages());
}

// TEST 29: printMessages contains sent message after send
@Test
public void testPrintMessagesAfterOneSend() {
    Message m = new Message("1111111111", 1, "+27718693002", "Hi Mike");
    m.SentMessage("1");
    assertTrue(Message.printMessages().contains("1111111111"));
 }
    // ===================================================
    // NEW TESTS: String Assertions ( FROM Lecturer Feedback)
    // ===================================================

    @Test
    public void testRegisterUserSuccessMessage() {
        String result = Login.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertEquals("User registered successfully.", result);
    }

    @Test
    public void testRegisterUserBadUsernameMessage() {
        String result = Login.registerUser("kyle!!!!!!!", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterUserBadPasswordMessage() {
        String result = Login.registerUser("kyl_1", "password", "Kyle", "Smith", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testRegisterUserBadPhoneMessage() {
        String result = Login.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    @Test
    public void testLoginSuccessMessage() {
        Login.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        String result = Login.returnLoginStatus("kyl_1", "Ch&sec@ke99!");
        assertEquals("Welcome Kyle, Smith it is great to see you again.", result);
    }

    @Test
    public void testLoginFailureMessage() {
        Login.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        String result = Login.returnLoginStatus("wrong", "wrong");
        assertEquals("Username or password incorrect, please try again.", result);
    }
}

