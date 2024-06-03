package Assignment3;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
    
        Scanner UserInput = new Scanner(System.in);
        taskservice taskService = new taskservice(100);
        
        taskService.Greeting();
        
        int OptionChoice = -10;

        while (OptionChoice != 0) {
            System.out.println("Here are the operations you can perform: ");
            
            System.out.println("1. Add more task.");
            System.out.println("2. Update the task.");
            System.out.println("3. Delete the task.");
            System.out.println("4. Search a task from the history.");
            System.out.println("5. Arrange the tasks.");
            System.out.println("6. Check the repeated tasks.");
            System.out.println("0. Exit");
            System.out.println("Please enter the option number to continue: ");
            
            OptionChoice = UserInput.nextInt();
            UserInput.nextLine(); 

            if (OptionChoice == 1) {
                taskService.AddTask();
            } else if (OptionChoice == 2) {
                taskService.UpdateTask();
            } else if (OptionChoice == 3) {
                taskService.DeleteTask();
            } else if (OptionChoice == 4) {
                taskService.SearchTask();
            } else if (OptionChoice == 5) {
                taskService.ReorderTask();
            } else if (OptionChoice == 6) {
                taskService.checkRepeated();
            } else if (OptionChoice != 0) {
                System.out.println("Invalid option number, please try again.");
            }

        }
        System.out.println("Bye Bye.");

        UserInput.close();
    }
}
