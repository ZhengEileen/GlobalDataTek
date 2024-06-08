package com.infosys.service;

import java.util.Scanner;
import java.util.Random;
import com.infosys.dao.UserDao;
import com.infosys.pojo.User;

public class UserService {

    private Scanner userInput;
    private UserDao userDao;
    private boolean[] usedIds;
    private int size;

    // Default constructor
    public UserService() {
        int size = 100; // Default size
        this.userInput = new Scanner(System.in);
        this.userDao = new UserDao(size);
        this.usedIds = new boolean[size];
        this.size = size;
    }

    // Constructor with specified size
    public UserService(int size) {
        this.userInput = new Scanner(System.in);
        this.userDao = new UserDao(size);
        this.usedIds = new boolean[size];
        this.size = size;
    }

    // Constructor with specified size and Scanner instance
    public UserService(Scanner userInput, int size) {
        this.userInput = userInput;
        this.userDao = new UserDao(size);
        this.usedIds = new boolean[size];
        this.size = size;
    }

    public boolean checkUser(String userName) {
        User temp = userDao.getUser(userName);
        return temp != null;
    }

    public int generateUserId() {
        Random randomNum = new Random();
        int userId = randomNum.nextInt(size);
        while (usedIds[userId]) {
            userId = randomNum.nextInt(size);
        }
        usedIds[userId] = true;
        return userId;
    }

    public User register() {
        System.out.println("Hi, Please enter your username:");
        String userName = userInput.nextLine();
        System.out.println("Hi " + userName + " :)");

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
                    System.out.println("Login successful. Welcome back, " + existingUser.getUserName() + "!");
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
            System.out.println("Please enter your email ID:");
            String emailId = userInput.nextLine();
            int userId = generateUserId();
            User newUser = new User(userName, userId, emailId, password, null, null, null);
            userDao.addUser(newUser);
            System.out.println("Registration successful. Welcome, " + newUser.getUserName() + "!");
            return newUser;
        }
    }
}


