package com.infosys.controller;

import java.util.Scanner;
import com.infosys.pojo.User;
import com.infosys.service.BookService;
import com.infosys.service.UserService;

public class MagicOfBooks {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); // Initialize scanner for user input
        UserService userService = new UserService(userInput, 100); // Create UserService with size 100
        BookService bookService = new BookService(userInput, 100); // Create BookService with size 100

        User user = new User(); // Initialize a new User object

        while (true) { // Outer loop to handle continuous login
            user = userService.register(); // Register or login the user

            if (user == null) { // If registration/login fails, exit
                System.out.println("Bye Bye.");
                userInput.close(); // Close the scanner
                return;
            }
            
            bookService.setCurrentUser(user); // Set the current user in BookService
            int optionChoice = -10; // Initialize the option choice variable

            while (optionChoice != 0) { // Inner loop for user operations
                System.out.println("Here are the operations you can perform: ");
                System.out.println("1. Add a new book.");
                System.out.println("2. Delete a book.");
                System.out.println("3. View all books.");
                System.out.println("4. Add book to 'New' list.");
                System.out.println("5. Delete book from 'New' list.");
                System.out.println("6. Add book to 'Favorite' list.");
                System.out.println("7. Delete book from 'Favorite' list.");
                System.out.println("8. Add book to 'Completed' list.");
                System.out.println("9. Delete book from 'Completed' list.");
                System.out.println("10. View your New list.");
                System.out.println("11. View your Favorite list.");
                System.out.println("12. View your Completed list.");
                System.out.println("13. Get book details by ID.");
                System.out.println("0. Exit");
                System.out.println("Please enter the option number to continue: ");

                optionChoice = userInput.nextInt(); // Get the user's choice
                userInput.nextLine(); // Consume the newline

                if (optionChoice == 1) {
                    bookService.addBook(); // Call addBook method
                } else if (optionChoice == 2) {
                    bookService.deleteBook(); // Call deleteBook method
                } else if (optionChoice == 3) {
                    bookService.showAllBooks(); // Call showAllBooks method
                } else if (optionChoice == 4) {
                    bookService.addNew(); // Call addNew method
                } else if (optionChoice == 5) {
                    bookService.deleteNew(); // Call deleteNew method
                } else if (optionChoice == 6) {
                    bookService.addFavorite(); // Call addFavorite method
                } else if (optionChoice == 7) {
                    bookService.deleteFavorite(); // Call deleteFavorite method
                } else if (optionChoice == 8) {
                    bookService.addCompleted(); // Call addCompleted method
                } else if (optionChoice == 9) {
                    bookService.deleteCompleted(); // Call deleteCompleted method
                } else if (optionChoice == 10) {
                    bookService.isNew(); // Call isNew method
                } else if (optionChoice == 11) {
                    bookService.isFavorite(); // Call isFavorite method
                } else if (optionChoice == 12) {
                    bookService.isCompleted(); // Call isCompleted method
                } else if (optionChoice == 13) {
                    bookService.selectBookById(); // Call selectBookById method
                } else if (optionChoice == 0) {
                    System.out.println("Bye Bye."); // Exit message
                } else {
                    System.out.println("Invalid option number, please try again."); // Invalid option message
                }
            }
            if (optionChoice == 0) {
                break; // Exit the outer while loop if option 0 is selected
            }
        }
        userInput.close(); // Close the scanner
    }
}


