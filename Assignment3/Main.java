package Assignment3;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
        taskService taskService = new taskService(100);
        
        taskService.greeting();
        
        int optionChoice = -10;

        while (optionChoice != 0) {
            System.out.println("Here are the operations you can perform: ");
            
            System.out.println("1. Add more task.");
            System.out.println("2. Update the task.");
            System.out.println("3. Delete the task.");
            System.out.println("4. Search a task from the history.");
            System.out.println("5. Arrange the tasks.");
            System.out.println("6. Check the repeated tasks.");
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
            } else if (optionChoice != 0) {
            	System.out.println("Invalid option number, please try again.");
            }

        }
        System.out.println("Bye Bye.");

        userInput.close();
	}
}

