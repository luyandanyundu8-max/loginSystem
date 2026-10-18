/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Nyundu
 */
public class Message {
     // ===============================================================
    // MESSAGE VARIABLES
    // ===============================================================

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private String messageStatus;

    // Stores messages sent during the program
    private static ArrayList<Message> sentMessages = new ArrayList<>();

    // Stores messages that were disregarded
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();

    // Stores messages saved for later
    private static ArrayList<Message> storedMessages = new ArrayList<>();

    // Total number of messages sent
    private static int totalMessages = 0;

    // ===============================================================
    // CONSTRUCTOR
    // ===============================================================

    public Message(
            String messageID,
            int messageNumber,
            String recipient,
            String message) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    // ===============================================================
    // GETTERS
    // ===============================================================

    public String getMessageID()   { return messageID; }
    public int getMessageNumber()  { return messageNumber; }
    public String getRecipient()   { return recipient; }
    public String getMessage()     { return message; }
    public String getMessageHash() { return messageHash; }

    // ===============================================================
    // SETTERS
    // ===============================================================

    public void setMessageID(String id)    { messageID = id; }
    public void setRecipient(String cell)  { recipient = cell; }
    public void setMessage(String text)    { message = text; }

    // ===============================================================
    // generateMessageID — random 10-digit number
    // ===============================================================

    public static String generateMessageID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            int digit = rand.nextInt(10);
            id = id + digit;
        }
        return id;
    }

    // ===============================================================
    // checkMessageID — must be <= 10 characters
    // ===============================================================

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // ===============================================================
    // checkRecipientCell — returns SUCCESS or FAILURE message
    // ===============================================================

    public String checkRecipientCell() {
        if (recipient.length() == 0 || recipient.charAt(0) != '+') {
            return "Cell phone number is incorrectly formatted or does "
                 + "not contain an international code. Please correct "
                 + "the number and try again.";
        }
        if (recipient.length() > 12) {
            return "Cell phone number is incorrectly formatted or does "
                 + "not contain an international code. Please correct "
                 + "the number and try again.";
        }
        return "Cell phone number successfully captured.";
    }

    // ===============================================================
    // checkMessageLength — returns SUCCESS or FAILURE message
    // ===============================================================

    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess +
                   "; please reduce the size.";
        }
    }

    // ===============================================================
    // createMessageHash — first2 : number : firstWordLastWord (UPPERCASE)
    // ===============================================================

    public String createMessageHash() {
         String firstTwo = messageID.substring(0, 2);
    int spaceIndex = message.indexOf(' ');
    String firstWord;
    String lastWord;

    if (spaceIndex == -1) {
        firstWord = message;
        lastWord = message;
    } else {
        firstWord = message.substring(0, spaceIndex);
        lastWord = message.substring(message.lastIndexOf(' ') + 1);
    }

    String cleanFirst = "";
    String cleanLast = "";

    for (int i = 0; i < firstWord.length(); i++) {
        if (Character.isLetter(firstWord.charAt(i))) {
            cleanFirst = cleanFirst + firstWord.charAt(i);
        }
    }
    for (int i = 0; i < lastWord.length(); i++) {
        if (Character.isLetter(lastWord.charAt(i))) {
            cleanLast = cleanLast + lastWord.charAt(i);
        }
    }

    this.messageHash = (firstTwo + ":" + messageNumber + ":" +
                        cleanFirst + cleanLast).toUpperCase();
    return this.messageHash;
    }

    // ===============================================================
    // SentMessage — "1"=Send  "2"=Disregard  "3"=Store
    // ===============================================================

   
    public String SentMessage(String option) {
    switch (option) {
        case "1":
            sentMessages.add(this);
            totalMessages++;
            messageStatus = "Sent";
            return "Message successfully sent.";
        case "2":
            disregardedMessages.add(this);
            messageStatus = "Disregarded";
            return "Press 0 to delete the message.";
        case "3":
            storedMessages.add(this);
            messageStatus = "Stored";
            storeMessage();
            return "Message successfully stored.";
        default:
            return "Invalid option.";
    }
}    

    // ===============================================================
    // storeMessage — append this message as JSON to messages.json
    // ===============================================================

    public void storeMessage() {
        try {
            String json = "{" +
                    "\"messageID\":\"" + messageID + "\"," +
                    "\"messageNumber\":" + messageNumber + "," +
                    "\"recipient\":\"" + recipient + "\"," +
                    "\"message\":\"" + message + "\"," +
                    "\"hash\":\"" + messageHash + "\"" +
                    "}";

            FileWriter writer = new FileWriter("messages.json", true);
            writer.write(json + "\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // ===============================================================
    // printMessages — returns all SENT messages as one String
    // ===============================================================

    public static String printMessages() {
        String all = "";
        for (int i = 0; i < sentMessages.size(); i++) {
            all = all + sentMessages.get(i).printMessageDetails() + "\n";
        }
        return all;
    }

    // ===============================================================
    // returnTotalMessages — count of sent messages
    // ===============================================================

    public static int returnTotalMessages() {
        return totalMessages;
    }

    // ===============================================================
    // printMessageDetails — display all fields of one message
    // ===============================================================

    public String printMessageDetails() {
        return "Message ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + message;
    }

    // ===============================================================
    // resetAll — clears static storage (used by unit tests)
    // ===============================================================

    public static void resetAll() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        totalMessages = 0;
    }

    // ===============================================================
    // START QUICKCHAT — welcome + menu loop
    // ===============================================================

    public static void startQuickChat(Scanner input) {

        System.out.println("\n=========================================");
        System.out.println("     Welcome to QuickChat.");
        System.out.println("=========================================");

        System.out.print("How many messages would you like to enter? ");
        int numberOfMessages = input.nextInt();
        input.nextLine();

        boolean running = true;
        while (running) {

            System.out.println("\n=========================================");
            System.out.println("            QUICKCHAT");
            System.out.println("=========================================");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            String option = input.nextLine();

            switch (option) {
                case "1":
                    sendMessages(input, numberOfMessages);
                    break;
                case "2":
                    System.out.println("\nComing Soon.");
                    break;
                case "3":
                    running = false;
                    System.out.println("\nThank you for using QuickChat.");
                    break;
                default:
                    System.out.println("\nInvalid option. Please choose 1, 2, or 3.");
            }
        }
    }

    // ===============================================================
    // sendMessages — loop that lets the user enter messages
    // ===============================================================

    public static void sendMessages(Scanner input, int numberOfMessages) {

        for (int i = 1; i <= numberOfMessages; i++) {

            System.out.println("\n-----------------------------------------");
            System.out.println("MESSAGE " + i);
            System.out.println("-----------------------------------------");

            // MESSAGE ID
            String messageID = Message.generateMessageID();
            System.out.println("Message ID generated: " + messageID);

            // RECIPIENT
            String recipient;
            while (true) {
                System.out.print("Enter recipient cellphone number: ");
                recipient = input.nextLine();

                Message temporaryMessage = new Message(
                        messageID, 1, recipient, "");

                String check = temporaryMessage.checkRecipientCell();
                System.out.println(check);

                if (check.equals("Cell phone number successfully captured.")) {
                    break;
                }
            }

            // MESSAGE
            String messageText;
            while (true) {
                System.out.print("Enter your message: ");
                messageText = input.nextLine();

                Message tempMsg = new Message(messageID, i, recipient, messageText);
                String lengthCheck = tempMsg.checkMessageLength();
                System.out.println(lengthCheck);

                if (lengthCheck.equals("Message ready to send.")) {
                    break;
                }
            }

            // CREATE MESSAGE
            Message msg = new Message(messageID, i, recipient, messageText);
            System.out.println("Message Hash: " + msg.getMessageHash());

            // SEND / DISREGARD / STORE
            System.out.println();
            System.out.println("1) Send");
            System.out.println("2) Disregard");
            System.out.println("3) Store");
            System.out.print("Choose an option: ");
            String action = input.nextLine();

            String result = msg.SentMessage(action);
            System.out.println(result);

            if (action.equals("1")) {
                System.out.println();
                System.out.println(msg.printMessageDetails());
            }
        }

        System.out.println("\nTotal messages sent: " +
                           Message.returnTotalMessages());
    }
}
 

