package com.infosys.controller;

import com.infosys.exception.InvalidInputException;
import com.infosys.pojo.User;
import com.infosys.service.BookService;
import com.infosys.service.UserService;

import java.util.Scanner;

public class MagicOfBooks {
    private UserService userService;
    private BookService bookService;
    private Scanner scanner;

    public MagicOfBooks(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to Magic of Books!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    register();
                } else if (choice == 2) {
                    login();
                } else if (choice == 0) {
                    System.out.println("Exited!");
                    running = false;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void register() {
        System.out.println("Registration");
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter user ID: ");
            Long userId = Long.parseLong(scanner.nextLine());
            System.out.print("Enter email ID: ");
            String emailId = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            userService.registerUser(username, userId, emailId, password);
            System.out.println("Registration successful!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for user ID. Please enter a valid number.");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            if (userService.loginUser(username, password)) {
                User user = userService.getUserDetails(username);
                new Thread(() -> userMenu(user, new Scanner(System.in))).start();
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
        }
    }

    private void userMenu(User user, Scanner scanner) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("Welcome " + user.getUserName() + "!");
            System.out.println("1. View all books");
            System.out.println("2. Select a book by ID");
            System.out.println("3. Logout");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    userService.viewAllBooks(user, bookService);
                } else if (choice == 2) {
                    bookService.selectBookById(user);
                } else if (choice == 3) {
                    System.out.println("Logging out...");
                    loggedIn = false;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        BookService bookService = new BookService();
        MagicOfBooks magicOfBooks = new MagicOfBooks(userService, bookService);
        magicOfBooks.start();
    }
}

