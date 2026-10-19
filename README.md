# Login & QuickChat System

This is my Programming 1A project for Part 1 and Part 2. It is made in Java using NetBeans.

## What it does
The program lets a user register and then login. It checks if the username, password and phone number are in the right format. 
After logging in, it opens the QuickChat menu where users can send, store, or disregard messages. Messages are saved to a JSON file.

## Rules
**Username:**
* must have an underscore (_)
* must be 5 characters or less

**Password:**
* must be at least 8 characters
* must have a capital letter
* must have a number
* must have a special character

**Phone number:**
* must start with a +
* must be a South African number (+27) followed by 9 digits

## Files
* `LoginSystem.java` - the main file that runs the program
* `Login.java` - the file that checks registration and login
* `Message.java` - the file that handles messages, hashes, and JSON storage
* `mainTest.java` - the tests for the code
* `messages.json` - the file where stored messages are saved

## How to run
1. Open the project in NetBeans
2. Run LoginSystem.java
3. Type in your details and register
4. Login with the same details
5. Use the QuickChat menu to send messages

## How to run the tests
1. Right click on mainTest.java
2. Click Test File
3. All 35 tests should pass

## References & Attribution
To fulfill the research requirement for this project, the following resources were used:
* **Phone Number Regex:** Oracle. (2024). *java.util.regex.Pattern*. Retrieved from https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
* **JSON File Writing:** Oracle. (2024). *java.io.FileWriter*. Retrieved from https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html
* **Message Hashing:** Oracle. (2024). *java.lang.String*. Retrieved from https://docs.oracle.com/javase/8/docs/api/java/lang/String.html

## Author
Luyanda Nyundu
