package com.infosys.controller;

import java.util.Scanner;

import com.infosys.pojo.User;
import com.infosys.service.TaskService;
import com.infosys.service.UserService;


public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        TaskService taskService = new TaskService();
        UserService userService = new UserService();

        User user = new User();

        while (true) { // Start of the loop to allow continuous login
            user = userService.register();

            if (user == null) {
                System.out.println("Bye Bye.");
                userInput.close();
                return;
            }

            int optionChoice = -10;

            if (user.getRole().equals("Client")) {
                while (optionChoice != 0) {
                    System.out.println("Here are the operations you can perform: ");
                    System.out.println("1. Add more tasks.");
                    System.out.println("2. Update a task.");
                    System.out.println("3. Delete a task.");
                    System.out.println("4. Search a task from the history.");
                    System.out.println("5. Arrange the tasks.");
                    System.out.println("6. Check for repeated tasks.");
                    System.out.println("7. Create a visitor account.");
                    System.out.println("0. Exit");
                    System.out.println("Please enter the option number to continue: ");

                    optionChoice = userInput.nextInt();
                    userInput.nextLine();

                    if (optionChoice == 1) {
                        taskService.addTask();
                    } else if (optionChoice == 2) {
                        taskService.updateTask();
                    } else if (optionChoice == 3) {
                        taskService.deleteTask();
                    } else if (optionChoice == 4) {
                        taskService.searchTask();
                    } else if (optionChoice == 5) {
                        taskService.reorderTask();
                    } else if (optionChoice == 6) {
                        taskService.checkRepeated();
                    } else if (optionChoice == 7) {
                        userService.createVisitor();
                    } else if (optionChoice != 0) {
                        System.out.println("Invalid option number, please try again.");
                    }
                }
                System.out.println("Bye Bye."); 
            } else if (user.getRole().equals("Visitor")) {
                while (optionChoice != 0) {
                    System.out.println("Here are the operations you can perform: ");
                    System.out.println("1. Check the assigned tasks");
                    System.out.println("2. Arrange the tasks.");
                    System.out.println("3. Mark the completed tasks.");
                    System.out.println("4. Show the status of task completion.");
                    System.out.println("0. Exit");
                    System.out.println("Please enter the option number to continue: ");

                    optionChoice = userInput.nextInt();
                    userInput.nextLine();

                    if (optionChoice == 1) {
                        taskService.showTasksAssignedTo(user.getUserName());
                    } else if (optionChoice == 2) {
                        taskService.reorderTask();
                    } else if (optionChoice == 3) {
                        taskService.markTaskCompleted();
                    } else if (optionChoice == 4) {
                        taskService.showTaskStatus();
                    } else if (optionChoice == 0) {
                        System.out.println("Visitor session ended."); 
                    } else {
                        System.out.println("Invalid option, please try again.");
                    }
                }
                System.out.println("Bye Bye."); 
            }
        } 
    }
}