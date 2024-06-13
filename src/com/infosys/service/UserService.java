package com.infosys.service;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.infosys.dao.UserDao;
import com.infosys.exception.PasswordException;
import com.infosys.exception.RoleSelectionException;
import com.infosys.exception.UserExistsException;
import com.infosys.exception.UserLimitException;
import com.infosys.pojo.User;

public class UserService {

    private Scanner userInput;
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
        userInput = new Scanner(System.in);
    }

    public User register() throws RoleSelectionException, PasswordException, UserExistsException, UserLimitException {
    	if (userDao.getUserCount() >= 11) {
            System.out.println("User limit exceeded. Cannot add more users.");
            System.out.println("Bye Bye.");
            System.exit(1);
        }
    	
        System.out.println("Hi, Please enter your First name:");
        String userFirstName = userInput.nextLine();
        System.out.println("Please enter your Last name:");
        String userLastName = userInput.nextLine();
        System.out.println("Hi " + userFirstName + " " + userLastName + " :)");
        
        int role = 0;
        while (role != 1 && role != 2) {
            System.out.println("What is your role? Press 1 for Client, or press 2 for Visitor.");
            try {
                role = userInput.nextInt();
                userInput.nextLine(); // Consume the newline character
                if (role != 1 && role != 2) {
                    System.out.println("Invalid role selection. Please press 1 for Client or 2 for Visitor.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                userInput.nextLine(); // Clear the invalid input
            }
        }

        if (role == 1) {
            while (true) {
                System.out.println("Hi, Client " + userFirstName + " " + userLastName + " please enter your username to register.");
                String userName = userInput.nextLine();

                User existingUser = userDao.getUser(userName);
                if (existingUser != null) {
                    if (existingUser.getFirstName().equals(userFirstName) && existingUser.getLastName().equals(userLastName)) {
                        // Existing user with the same full name, prompt for password
                        while (true) {
                            System.out.println("User already exists. Please enter your password:");
                            String password = userInput.nextLine();
                            if (password.equals("0")) {
                                return null; // return null to exit the method
                            }
                            if (password.equals(existingUser.getPassword())) {
                                System.out.println("Login successful. Welcome back, " + existingUser.getFirstName() + "!");
                                return existingUser;
                            } else {
                                System.out.println("Incorrect password. Please try again.");
                                System.out.println("Type 0 if you want to exit.");
                            }
                        }
                    } else {
                        // Username taken by a different person
                        System.out.println("The username is unavailable. Please enter a different username.");
                    }
                } else {
                    // New user, prompt for password to register
                    while (true) {
                        System.out.println("Please enter your password:");
                        String password = userInput.nextLine();
                        if (password.length() > 20) {
                            System.out.println("Password is too long. Please enter a password with less than 20 characters.");
                        } else {
                            User newUser = new User(userFirstName, userLastName, userName, password, "Client");
                            try {
                                userDao.addUser(newUser);
                            } catch (UserLimitException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Bye Bye.");
                                System.exit(1);
                            }
                            System.out.println("Registration successful. Welcome, " + userFirstName + " " + userLastName + "!");
                            return newUser;
                        }
                    }
                }
            }
        } else if (role == 2) {
            System.out.println("Hi, Visitor " + userFirstName + " " + userLastName + ".");
            System.out.println("Please enter your username.");
            String userName = userInput.nextLine();
            User visitor = userDao.getUser(userName);
            if (visitor == null) {
                System.out.println("Sorry, You do not have permission to view tasks.");
                return null;
            } else {
                return visitor;
            }
        } else {
            throw new RoleSelectionException("Invalid role selection. Please press 1 for Client or 2 for Visitor.");
        }
    }

    public void createVisitor() throws UserExistsException, UserLimitException {
        System.out.println("Creating a visitor account.");
        System.out.println("Please enter the visitor's First name:");
        String visitorFirstName = userInput.nextLine();
        System.out.println("Please enter the visitor's Last name:");
        String visitorLastName = userInput.nextLine();
        System.out.println("Please enter the visitor's Username:");
        String visitorUserName = userInput.nextLine();

        if (checkUser(visitorUserName)) {
            throw new UserExistsException("The username is unavailable. Please enter a different username.");
        }

        User newVisitor = new User(visitorFirstName, visitorLastName, visitorUserName, null, "Visitor");
        try {
            userDao.addUser(newVisitor);
        } catch (UserLimitException e) {
            System.out.println(e.getMessage());
            System.out.println("Bye Bye.");
            System.exit(1);
        }
        System.out.println("Visitor account created successfully for " + visitorFirstName + " " + visitorLastName + ".");
    }

    public boolean checkUser(String userName) {
        boolean flag;
        User temp = userDao.getUser(userName);
        flag = temp != null;
        return flag;
    }
}
