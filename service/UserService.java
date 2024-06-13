package com.infosys.service;

import java.util.Scanner;

import com.infosys.dao.UserDao;
import com.infosys.pojo.User;

public class UserService {

    private Scanner userInput;
    private UserDao userDao;

    public UserService() {
        userInput = new Scanner(System.in);
        userDao = new UserDao();
    }

    public boolean checkUser(String userName) {
        User temp = userDao.getUser(userName);
        if (temp != null) {
            return true;
        } else {
            return false;
        }
    }

    public User register() {
        System.out.println("Hi, Please enter your First name:");
        String userFirstName = userInput.nextLine();
        System.out.println("Please enter your Last name:");
        String userLastName = userInput.nextLine();
        System.out.println("Hi " + userFirstName + " " + userLastName + " :)");
        System.out.println("What is your role? Press 1 for Client, or press 2 for Visitor.");
        int role = userInput.nextInt();
        userInput.nextLine();

        if (role == 1) {
            System.out.println("Hi, Client " + userFirstName + " " + userLastName + " please enter your username to register.");
            String userName = userInput.nextLine();

            if (checkUser(userName)) {
                // Existing user, prompt for password
                while (true) {
                    System.out.println("User already exists. Please enter your password:");
                    String password = userInput.nextLine();
                    if (password.equals("0")) {
                        System.out.println("Exiting... Bye Bye :)");
                        return null; // return null to exit the method
                    }
                    User existingUser = userDao.getUser(userName);
                    if (password.equals(existingUser.getPassword())) {
                        System.out.println("Login successful. Welcome back, " + existingUser.getFirstName() + "!");
                        return existingUser;
                    } else {
                        System.out.println("Incorrect password. Please try again.");
                        System.out.println("Type 0 if you want to exit.");
                    }
                }
            } else {
                // New user, prompt for password to register
                System.out.println("Please enter your password:");
                String password = userInput.nextLine();
                User newUser = new User(userFirstName, userLastName, userName, password, "Client");
                userDao.addUser(newUser);
                System.out.println("Registration successful. Welcome, " + userFirstName + " " + userLastName + "!");
                return newUser;
            }

        } else if (role == 2) { // check if role is 2 for Visitor
            System.out.println("Hi, Visitor " + userFirstName + " " + userLastName + " .");
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
            System.out.println("Invalid role selection. Please try again.");
            return null;
        }
    }

    public void createVisitor() {
        System.out.println("Creating a visitor account.");
        System.out.println("Please enter the visitor's First name:");
        String visitorFirstName = userInput.nextLine();
        System.out.println("Please enter the visitor's Last name:");
        String visitorLastName = userInput.nextLine();
        System.out.println("Please enter the visitor's Username:");
        String visitorUserName = userInput.nextLine();

        User newVisitor = new User(visitorFirstName, visitorLastName, visitorUserName, null, "Visitor");
        userDao.addUser(newVisitor);
        System.out.println("Visitor account created successfully for " + visitorFirstName + " " + visitorLastName + ".");
    }
}

	