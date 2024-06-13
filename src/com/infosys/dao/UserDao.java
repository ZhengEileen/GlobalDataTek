package com.infosys.dao;

import com.infosys.pojo.User;
<<<<<<< HEAD
import com.infosys.exception.UserLimitException;
=======
>>>>>>> 889d62ab8b34eaf3a3c91ab47d7270522fcc4c8d

public class UserDao {

    private User[] users;
    private int userCount;

<<<<<<< HEAD
    public UserDao() {
        users = new User[10];
        userCount = 0;
    }

    public void addUser(User user) throws UserLimitException {
        if (userCount >= users.length) {
            throw new UserLimitException("User limit exceeded. Cannot add more users.");
        } else {
            users[userCount++] = user;
        }
    }

    public User getUser(String userName) {
        for (User value : users) {
            if (value != null && value.getUserName().equals(userName)) {
                return value;
            }
        }
        return null;
    }

    public void updateUser(User newUser) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUserName().equals(newUser.getUserName())) {
                users[i] = newUser;
                break;
=======
    // Default constructor with default size 100
    public UserDao() {
        int size = 100; // Set default size to 100
        users = new User[size]; // Initialize the users array with the default size
        userCount = 0; // Initialize user count to 0
    }

    // Constructor with specified size
    public UserDao(int size) {
        users = new User[size]; // Initialize the users array with the specified size
        userCount = 0; // Initialize user count to 0
    }

    // Method to add a user to the array
    public void addUser(User user) {
        if (userCount < users.length) { // Check if there is space in the array
            users[userCount] = user; // Add the user to the array
            userCount++; // Increment the user count
        } else {
            System.out.println("Array is full. Cannot add more users."); // Print a message if the array is full
        }
    }

    // Method to get a user by their username
    public User getUser(String userName) {
        for (User user : users) { // Iterate over the users array
            if (user != null && user.getUserName().equals(userName)) { // Check if the user is not null and the username matches
                return user; // Return the user if found
            }
        }
        return null; // Return null if the user is not found
    }

    // Method to update a user
    public void updateUser(User newUser) {
        for (int i = 0; i < userCount; i++) { // Iterate over the users array
            if (users[i].getUserName().equals(newUser.getUserName())) { // Check if the username matches
                users[i] = newUser; // Update the user with the new user
                break; // Exit the loop after updating
>>>>>>> 889d62ab8b34eaf3a3c91ab47d7270522fcc4c8d
            }
        }
    }

<<<<<<< HEAD
    public void deleteUser(User oldUser) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUserName().equals(oldUser.getUserName())) {
                for (int j = i; j < userCount - 1; j++) {
                    users[j] = users[j + 1];
                }
                users[userCount - 1] = null;
                userCount--;
                break;
            }
        }
    }
    
    public int getUserCount() {
        return userCount;
    }
}
=======
    // Method to delete a user
    public void deleteUser(User oldUser) {
        for (int i = 0; i < userCount; i++) { // Iterate over the users array
            if (users[i].getUserName().equals(oldUser.getUserName())) { // Check if the username matches
                for (int j = i; j < userCount - 1; j++) { // Shift the users to the left to fill the gap
                    users[j] = users[j + 1];
                }
                users[userCount - 1] = null; // Clear the last user element
                userCount--; // Decrement the user count
                break; // Exit the loop after deleting
            }
        }
    }
}

>>>>>>> 889d62ab8b34eaf3a3c91ab47d7270522fcc4c8d
