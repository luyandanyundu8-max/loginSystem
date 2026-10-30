/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author Nyundu
 */

 
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Before
    public void setUp() {
        Message.resetAll(); // Clear sent/stored arrays before each test
    }

    // PART 2 TESTS
    @Test
    public void testMessageIDCreated() {
        String id = Message.generateMessageID();
        assertEquals(10, id.length());
    }

    @Test
    public void testCheckMessageIDValid() {
        Message m = new Message("1234567890", 1, "+27718693002", "Hi Mike");
        assertTrue(m.checkMessageID());
    }

    @Test
    public void testCheckMessageIDInvalid() {
        Message m = new Message("12345678901", 1, "+27718693002", "Hi Mike");
        assertFalse(m.checkMessageID());
    }

    @Test
    public void testRecipientCellSuccess() {
        Message m = new Message("1234567890", 1, "+27718693002", "Hi Mike");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message m = new Message("1234567890", 1, "08575975889", "Hi Keegan");
        assertTrue(m.checkRecipientCell().contains("incorrectly formatted"));
    }

    @Test
    public void testMessageLengthSuccess() {
        Message m = new Message("1234567890", 1, "+27718693002", "Hi Mike");
        assertEquals("Message ready to send.", m.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "";
        for (int i = 0; i < 260; i++) { longMsg += "a"; }
        Message m = new Message("1234567890", 1, "+27718693002", longMsg);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", m.checkMessageLength());
    }

    @Test
    public void testMessageHash() {
        Message m = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", m.getMessageHash());
    }

    @Test
    public void testSentMessageSend() {
        Message m = new Message("1234567890", 1, "+27718693002", "Hi Mike");
        assertEquals("Message successfully sent.", m.SentMessage("1"));
        assertEquals(1, Message.returnTotalMessages());
    }

    @Test
    public void testSentMessageDisregard() {
        Message m = new Message("1234567890", 1, "+27718693002", "Hi Mike");
        assertEquals("Press 0 to delete the message.", m.SentMessage("2"));
        assertEquals(0, Message.returnTotalMessages());
    }

    @Test
    public void testSentMessageStore() {
        Message m = new Message("1234567890", 1, "+27718693002", "Hi Mike");
        assertEquals("Message successfully stored.", m.SentMessage("3"));
    }
}   

