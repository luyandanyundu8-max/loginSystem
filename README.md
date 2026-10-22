# Login & QuickChat System

This is my Programming 1A project for Part 1, Part 2, and Part 3. It is made in Java using NetBeans.

## What it does
The program lets a user register and then login. It checks if the username, password, and phone number are in the right format. 
After logging in, it opens the QuickChat menu where users can send, store, or disregard messages. Messages are saved to a JSON file. Finally, a fourth menu option allows users to read that JSON file, populate arrays, and search, delete, or display a report of the stored messages.

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
* `Message.java` - the file that handles sending messages and writing to JSON
* `StoredMessages.java` - the file that reads the JSON, populates arrays, and handles the Part 3 menu (search, delete, report)
* `mainTest.java` - the tests for Part 1 and Part 2
* `StoredMessagesTests.java` - the tests for Part 3
* `messages.json` - the file where stored messages are saved

## How to run
1. Open the project in NetBeans
2. Run LoginSystem.java
3. Type in your details and register
4. Login with the same details
5. Use the QuickChat menu to send messages, or choose Option 4 for Stored Messages.

## How to run the tests
1. Right-click `mainTest.java` -> Test File (Runs 35 tests)
2. Right-click `StoredMessagesTests.java` -> Test File (Runs 5 tests)
3. All 40 tests should pass.

## Part 3: Stored Messages Features
The `StoredMessages` class reads the JSON file into 5 arrays. Through the menu, users can:
* Display sender and recipient of all stored messages.
* Display the longest message.
* Search for a message by ID.
* Search for messages sent to a specific recipient.
* Delete a message using its hash.
* Display a full report.

## References & Attribution
To fulfill the research requirement for this project, the following resources were used:
* **Phone Number Regex:** Oracle. (2024). *java.util.regex.Pattern*. Retrieved from https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
* **Reading/Writing JSON Files:** Oracle. (2024). *java.io.FileWriter* and *java.util.Scanner*. Retrieved from https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html

## Author
Luyanda Nyundu
