/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
// Part 1 - Login System Project
package loginsystem;

/**
 *
 * @author Nyundu
 */
// This is the main class of my program.
// It shows the menu and lets the user register and then login.

import java.util.Scanner;
public class LoginSystem {

    /**
     * @param args the command line arguments
     */
    // This is where the program starts.
    // It welcomes the user, then calls register and login methods.
    public static void main(String[] args) {
          // Make a Scanner so we can read what the user types
         Scanner scan = new Scanner(System.in);
        
         // Show welcome messages to the user
        System.out.println(" | welcome to  registration  |");
        System.out.println(" |please enter your details to create an account |");
        System.out.println(" |username must contain '_' and be max 5 characters|");
           // Call the register method so the user can sign up
       registerUser(scan);
         // After registering, ask the user to login
       System.out.println();
       System.out.println(" |now please login |");
       loginUser(scan);
    }
     
          // This method asks the user for their details to register.
          // It keeps asking until the user types valid info for each field.
    public static void registerUser(Scanner scan) {
         // These will hold the user's details
        String firstName, lastName, username, password, phone;
        
        System.out.println();
        System.out.println("enter your details for Registration ");
          // Ask for the user's first name
        System.out.print("Enter your name: ");
        firstName = scan.nextLine();
            // Ask for the user's surname
        System.out.print("Enter your surname: ");
        lastName = scan.nextLine();
           // while loop that Keep asking for a username until it is valid
          // Valid means: has an underscore and is 5 characters or less
        while (true) {
            System.out.print("Enter username (must contain _ and be <= 5 characters): ");
            username = scan.nextLine();
             // Use the login class to check if the username is okay
            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break; // Stop the loop because it's valid
            }
            // Tell the user what's wrong and ask again
            else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }
           // Keep asking for a password until it is valid
          // Valid means: 8+ characters, has a capital, a number, and a special character
        while (true) {
            System.out.print("Enter password (>= 8 chars, 1 capital, 1 number, 1 special char): ");
            password = scan.nextLine();
         // Use the login class to check the password
            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;// Stop the loop because it's valid
            }
              // Tell the user what's wrong and ask again
            else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }
          // Keep asking for a phone number until it is valid
         // Valid means: starts with '+' and has a country code and number
        while (true) {
            System.out.print("Enter cell phone number (must start with + and followed by up to 10 digits): ");
            phone = scan.nextLine();
            // Use the login class to check the phone number
            if (login.checkCellPhoneNumber(phone)) {
                System.out.println("Cell phone number successfully added.");
                break;
            }
            else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }
          // Now save all the details using the login class
        String result = login.registerUser(username, password, firstName, lastName, phone);
        System.out.println(result);
         // If registration worked, show the user their details
        if (result.equals("User registered successfully.")) {
            System.out.println();
            System.out.println("Registration complete you are now on LT-CHATS");
            System.out.println("Name: " + firstName);
            System.out.println("Surname: " + lastName);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Phone: " + phone);
        }
    }
         // This method lets the user login with their username and password.
         // If the details are wrong, it keeps asking until they get it right.
   public static void loginUser(Scanner scan) {
    String username, password;   // These will hold the login details the user types

    System.out.println();
    System.out.println("now enter your details to Login");
         // Keep asking until the login works
    while (true) {
         // Ask for the username and password
        System.out.print("Enter username: ");
        username = scan.nextLine();
        System.out.print("Enter password: ");
        password = scan.nextLine();
          // Use the login class to check if the details are correct
        String message = login.returnLoginStatus(username, password);
        System.out.println(message);

        // If login succeeded, break out of the loop
        if (!message.equalsIgnoreCase("Username or password incorrect, please try again.")) {
            break;
        }

        // If login failed, loop back and ask again
        System.out.println();
        System.out.println("Please try again.");
        System.out.println();
    }
  }
}
 
