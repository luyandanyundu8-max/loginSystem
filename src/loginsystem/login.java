/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author Nyundu
 */
import java.util.regex.Pattern;
public class login {
    // variables to store the registered user details , but must as empty 
   public static String savedUsername = "";
   public static String savedPassword = "";
   public static String savedFirstName = "";
   public  static String savedLastName = "";
   public static String savedPhone = "";
   
   // Declare regex pattern
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+[0-9]{1,3}[0-9]{1,10}$");// country code (1-3)+ number(1-10)
// this pattern checks if phone number start with + and has numbers after it 
    
    // this method checks if username is correctly formatted 
    public static boolean checkUserName(String username) {
        if (username.length() == 0 || username.length() > 5) {
            return false; // this means invalid username 
        }
        // variable to check if username has an underscore
        boolean hasUnderscore = false;
        // a for loop to loop though each character in the useername 
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == '_') {
                hasUnderscore = true;
            }
        }
        return hasUnderscore;
    }
//  this method checks if password meets complexity requirements 
    public static boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        // a for loop to loop though each character in the password
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        
        return hasCapital && hasNumber && hasSpecial;
    }

    public static boolean checkCellPhoneNumber(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
// this method handles user registration and validates all the details 
    public static String registerUser(String username, String password, String firstName, String lastName, String phone) {
        // this if statement checks if username is valid 
        if (checkUserName(username) == false) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        //this if statements checks if password meets complexity rule ( 8 characters , capital, number and special character )
        if (checkPasswordComplexity(password) == false) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (checkCellPhoneNumber(phone) == false) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        // if everything goes accordingly then save 
        savedUsername = username;
        savedPassword = password;
        savedFirstName = firstName;
        savedLastName = lastName;
        savedPhone = phone;
        
        return "User registered successfully.";
    }
// this method checks if the username entered and password match with the saved ones 
    public static boolean loginUser(String username, String password) {
        if (savedUsername.equals(username) && savedPassword.equals(password)) {
            return true; // this would mean that login details are correct 
        }
        return false; // this would mean login details are not correct 
    }
// this method returns a message as to whether loginwas successfull or not
    public static String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + savedFirstName + ", " + savedLastName + " it is great to see you again.";
        }
        else {
            return "Username or password incorrect, please try again.";// if login, fails show error message 
        }
    }
}

