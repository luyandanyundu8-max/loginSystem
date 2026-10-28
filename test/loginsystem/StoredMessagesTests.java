/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;
import org.junit.Before;
import org.junit.Test;
import java.io.FileWriter;
import java.util.Scanner;
import static org.junit.Assert.*;

/**
 *
 * @author Nyundu
 */
public class StoredMessagesTests {
  private StoredMessages sm;

    @Before
    public void setUp() throws Exception {
        // 1. Create a temporary JSON file with the exact test data from the rubric
        FileWriter writer = new FileWriter("messages.json", false);
        
        // Test Data Message 1 (Sent)
        writer.write("{\"messageID\":\"ID1\",\"messageNumber\":1,\"recipient\":\"+27834557896\",\"message\":\"Did you get the cake?\",\"hash\":\"H1\",\"status\":\"Sent\"}\n");
        
        // Test Data Message 2 (Stored)
        writer.write("{\"messageID\":\"ID2\",\"messageNumber\":2,\"recipient\":\"+27838884567\",\"message\":\"Where are you? You are late! I have asked you to be on time.\",\"hash\":\"H2\",\"status\":\"Stored\"}\n");
        
        // Test Data Message 3 (Disregard)
        writer.write("{\"messageID\":\"ID3\",\"messageNumber\":3,\"recipient\":\"+27834484567\",\"message\":\"Yohoooo, I am at your gate.\",\"hash\":\"H3\",\"status\":\"Disregard\"}\n");
        
        // Test Data Message 4 (Sent)
        writer.write("{\"messageID\":\"0838884567\",\"messageNumber\":4,\"recipient\":\"0838884567\",\"message\":\"It is dinner time!\",\"hash\":\"H4\",\"status\":\"Sent\"}\n");
        
        // Test Data Message 5 (Stored)
        writer.write("{\"messageID\":\"ID5\",\"messageNumber\":5,\"recipient\":\"+27838884567\",\"message\":\"Ok, I am leaving without you.\",\"hash\":\"H5\",\"status\":\"Stored\"}\n");
        
        writer.close();

        // 2. Initialize the StoredMessages class so it reads the temporary file
        sm = new StoredMessages();
    }

    // Test 1: Sent Messages array correctly populated
    @Test
    public void testSentMessagesArrayPopulated() {
        // Check if the array has exactly 2 "Sent" messages as per the rubric
        String[] sent = sm.getSentMessages();
        assertEquals(2, sent.length);
        assertEquals("Did you get the cake?", sent[0]);
        assertEquals("It is dinner time!", sent[1]);
    }

    // Test 2: Display the longest message
    @Test
    public void testLongestMessage() {
        // Simulate the user typing 'b' for longest message, then 'x' to go back
        Scanner input = new Scanner("b\nx\n");
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        
        sm.storedMessagesMenu(input);
        
        // The system should return the longest message from the test data
        assertTrue(out.toString().contains("Where are you? You are late! I have asked you to be on time."));
    }

    // Test 3: Search for a message by ID
    @Test
    public void testSearchMessageID() {
        // Simulate typing 'c', entering the ID, then 'x' to go back
        Scanner input = new Scanner("c\n0838884567\nx\n");
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        
        sm.storedMessagesMenu(input);
        
        // The system should return "It is dinner time!"
        assertTrue(out.toString().contains("It is dinner time!"));
    }

    // Test 4: Search all messages for a particular recipient
    @Test
    public void testSearchRecipient() {
        // Simulate typing 'd', entering the recipient number, then 'x' to go back
        Scanner input = new Scanner("d\n+27838884567\nx\n");
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        
        sm.storedMessagesMenu(input);
        
        // The system should return the message linked to that recipient
        assertTrue(out.toString().contains("Where are you? You are late! I have asked you to be on time."));
    }

    // Test 5: Delete a message using a hash
    @Test
    public void testDeleteMessage() {
        // Simulate typing 'e', entering the hash to delete, then 'x' to go back
        Scanner input = new Scanner("e\nH2\nx\n");
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        
        sm.storedMessagesMenu(input);
        
        // The system should return the successful deletion message
        assertTrue(out.toString().contains("Message successfully deleted."));
    }  
        // Test 6: Verify JSON Parsing (Boundary test)
    @Test
    public void testJsonParsingBoundary() {
        // This proves we can load the data into the arrays correctly
        String[] stored = sm.getStoredMessages();
        assertEquals(2, stored.length); // We expect 2 stored messages from the @Before setup
        assertEquals("Where are you? You are late! I have asked you to be on time.", stored[0]);
    }

    
        // Test 7: Verify Menu Flow (Report generation)
    @Test
    public void testDisplayReportMenuFlow() {
        // Simulate the user typing 'f' to display the report, then 'x' to exit
        Scanner input = new Scanner("f\nx\n");
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        
        sm.storedMessagesMenu(input);
        
        String output = out.toString();
        
        // The system should print the Hash, Recipient, and Message of the STORED messages
        assertTrue(output.contains("Message Hash: H2"));
        assertTrue(output.contains("Recipient: +27838884567"));
        assertTrue(output.contains("Message: Where are you? You are late! I have asked you to be on time."));
    }
}
