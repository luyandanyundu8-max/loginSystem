/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Nyundu
 */
public class StoredMessages {
    // The name of the JSON file we are reading from
    private final String jsonFile = "messages.json";

    // The 5 required arrays for Part 3. 
    // We start them at size 0, and they will grow dynamically.
    private String[] sentMessages = new String[0];
    private String[] disregardedMessages = new String[0];
    private String[] storedMessages = new String[0];
    private String[] messageHashes = new String[0];
    private String[] messageIDs = new String[0];

    // Additional arrays to help us search and display details
    private String[] recipients = new String[0];
    private String[] senders = new String[0];
    private String[] statuses = new String[0];

    // A list to temporarily hold the data before it goes into the arrays
    private final List<MessageRecord> messages = new ArrayList<>();

    // Inner class to hold the data for a single message
    private static class MessageRecord {
        String id;
        String hash;
        String recipient;
        String text;
        String status;
        String sender;

        MessageRecord(String id, String hash, String recipient, String text, String status, String sender) {
            this.id = id;
            this.hash = hash;
            this.recipient = recipient;
            this.text = text;
            this.status = status;
            this.sender = sender;
        }
    }

    // Constructor: runs when we create a new StoredMessages object
    public StoredMessages() {
        loadStoredMessagesFromJSON();
        populateArrays();
    }

    // This method reads the JSON file and creates MessageRecord objects
    public void loadStoredMessagesFromJSON() {
        messages.clear();
        try {
            File file = new File(jsonFile);
            if (!file.exists()) return;

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                String id = line.substring(line.indexOf("\"messageID\":\"") + 13, line.indexOf("\",\"messageNumber\""));
                String recipient = line.substring(line.indexOf("\"recipient\":\"") + 13, line.indexOf("\",\"message\""));
                String text = line.substring(line.indexOf("\"message\":\"") + 11, line.indexOf("\",\"hash\""));
                
                int hashStart = line.indexOf("\"hash\":\"") + 8;
                int hashEnd = line.indexOf("\"", hashStart);
                String hash = line.substring(hashStart, hashEnd);
                
                String status = "Stored";
                if (line.contains("\"status\":\"")) {
                    int statusStart = line.indexOf("\"status\":\"") + 10;
                    int statusEnd = line.indexOf("\"", statusStart);
                    status = line.substring(statusStart, statusEnd);
                }

                messages.add(new MessageRecord(id, hash, recipient, text, status, "Developer"));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading JSON file.");
        }
    }

    // This method moves the data from the list into the 5 required arrays
    public final void populateArrays() {
        // Reset the arrays to empty before filling them up
        sentMessages = new String[0];
        disregardedMessages = new String[0];
        storedMessages = new String[0];
        messageHashes = new String[0];
        messageIDs = new String[0];
        recipients = new String[0];
        senders = new String[0];
        statuses = new String[0];

        // Loop through every message we read from the JSON file
        for (MessageRecord msg : messages) {
            // Add the data to the parallel arrays
            messageHashes = addToArray(messageHashes, msg.hash);
            messageIDs = addToArray(messageIDs, msg.id);
            recipients = addToArray(recipients, msg.recipient);
            senders = addToArray(senders, msg.sender);
            statuses = addToArray(statuses, msg.status);

            // Put the message text into the correct array based on its status
            if ("Sent".equalsIgnoreCase(msg.status)) {
                sentMessages = addToArray(sentMessages, msg.text);
            } else if ("Disregard".equalsIgnoreCase(msg.status) || "Disregarded".equalsIgnoreCase(msg.status)) {
                disregardedMessages = addToArray(disregardedMessages, msg.text);
            } else if ("Stored".equalsIgnoreCase(msg.status)) {
                storedMessages = addToArray(storedMessages, msg.text);
            }
        }
    }

    // Helper method to resize an array so we can add a new item to it
    private String[] addToArray(String[] array, String value) {
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }

    // This method displays the Part 3 menu and handles the user's choices
   
        public void storedMessagesMenu(Scanner input) {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID");
            System.out.println("d. Search messages for a particular recipient");
            System.out.println("e. Delete a message using the message hash");
            System.out.println("f. Display a full report");
            System.out.println("x. Back to main menu");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            switch (choice) {
                case "a":
                    // Loop through stored messages and print sender/recipient
                    for (int i = 0; i < storedMessages.length; i++) {
                        System.out.println("Sender: " + senders[i] + " | Recipient: " + recipients[i]);
                    }
                    break;
                case "b":
                    // Search for the longest message
                    String longest = "";
                    for (String s : storedMessages) {
                        if (s.length() > longest.length()) longest = s;
                    }
                    System.out.println("Longest Message: " + longest);
                    break;
                case "c":
                    // Search through the internal 'messages' list (safe from crashes)
                    System.out.print("Enter Message ID: ");
                    String searchID = input.nextLine();
                    for (MessageRecord msg : messages) {
                        if (msg.id.equals(searchID)) {
                            System.out.println("Found! Recipient: " + msg.recipient + " | Message: " + msg.text);
                        }
                    }
                    break;
                case "d":
                    // Search through the internal 'messages' list (safe from crashes)
                    System.out.print("Enter Recipient: ");
                    String searchRecip = input.nextLine();
                    for (MessageRecord msg : messages) {
                        if (msg.recipient.equals(searchRecip)) {
                            System.out.println("Message: " + msg.text);
                        }
                    }
                    break;
                case "e":
                    // Delete a message using its hash
                    System.out.print("Enter Hash to delete: ");
                    String deleteHash = input.nextLine();
                    messages.removeIf(msg -> msg.hash.equals(deleteHash));
                    populateArrays(); // Rebuild arrays so the deletion takes effect
                    System.out.println("Message successfully deleted.");
                    break;
                case "f":
                    // Print the full report
                    for (int i = 0; i < storedMessages.length; i++) {
                        System.out.println("---------------------------");
                        System.out.println("Message Hash: " + messageHashes[i]);
                        System.out.println("Recipient: " + recipients[i]);
                        System.out.println("Message: " + storedMessages[i]);
                    }
                    break;
                case "x":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
     // ===============================================================
    // PASTE THE GETTERS HERE
    // ===============================================================
    public String[] getSentMessages() { return sentMessages; }
    public String[] getDisregardedMessages() { return disregardedMessages; }
    public String[] getStoredMessages() { return storedMessages; }
}

