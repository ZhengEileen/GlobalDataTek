package JavaAssignment;

import java.util.Arrays;
import java.util.Scanner;

public class Assignment2 {

    public static void main(String[] args) {
        Scanner UserInput = new Scanner(System.in);
        System.out.println("Hi, Please enter your name:");
        String UserName = UserInput.nextLine();
        System.out.println("Hi " + UserName + " :)");

        TodoManager todoManager = new TodoManager();
        int NumberTasks = todoManager.NumberTasks();
        String[] tasks = todoManager.GetTask(NumberTasks);

        int choice = -1;

        while (choice != 0) {
            System.out.println("\nMenu:");
            System.out.println("1. Add the task.");
            System.out.println("2. Update the task.");
            System.out.println("3. Delete the task.");
            System.out.println("4. Search a task from the history.");
            System.out.println("5. Arrange the tasks.");
            System.out.println("6. Check the repeated tasks.");
            System.out.println("0. Exit");
            System.out.print("Please enter the option number to continue: ");
            choice = UserInput.nextInt();
            UserInput.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    tasks = todoManager.AddTask(tasks);
                    break;
                case 2:
                    tasks = todoManager.UpdateTask(tasks);
                    break;
                case 3:
                    tasks = todoManager.DeleteTask(tasks);
                    break;
                case 4:
                    todoManager.SearchTask(tasks);
                    break;
                case 5:
                    todoManager.ReOrder(tasks);
                    break;
                case 6:
                    todoManager.checkRepeated(tasks);
                    break;
                case 0:
                    System.out.println("Bye Bye.");
                    break;
            }
        }

        UserInput.close();
    }
}

class TodoManager {
    private Scanner UserInput;

    public TodoManager() {
        UserInput = new Scanner(System.in);
    }

    public int NumberTasks() {
        System.out.println("How many tasks would you like to accomplish today?");
        int Number = UserInput.nextInt();
        UserInput.nextLine();
        return Number;
    }

    public String[] GetTask(int NumberTasks) {
        System.out.println("Please type your " + NumberTasks + " tasks for today, each task should end with a period:");
        String temp = UserInput.nextLine();
        String[] tasks = temp.split("\\.");
        return tasks;
    }

    public String[] AddTask(String[] tasks) {
        System.out.println("Would you like to add a new task to your daily tasks?");
        System.out.println("Type \"yes\" or \"Yes\" if you want to.");
        System.out.println("Type \"finish\" or \"Finish\" if you have finished adding your tasks.");
        String UserDecision = UserInput.nextLine();

        while (!UserDecision.equalsIgnoreCase("finish")) {
            if (UserDecision.equalsIgnoreCase("yes")) {
                System.out.println("Please type your new task below.");
                String NewTask = UserInput.nextLine();
                tasks = Arrays.copyOf(tasks, tasks.length + 1);
                tasks[tasks.length - 1] = NewTask;
                System.out.println("Your new task added successfully!");
            }
            System.out.println("Would you like to add a new task to your daily tasks?");
            System.out.println("Type \"yes\" or \"Yes\" if you want to.");
            System.out.println("Type \"finish\" or \"Finish\" if you have finished adding your tasks.");
            UserDecision = UserInput.nextLine();
        }
        return tasks;
    }

    public String[] UpdateTask(String[] tasks) {
        System.out.println("The following are your current tasks:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }

        System.out.println("Please enter the listed number of the task you want to update:");
        System.out.println("Type \"-1\" if you have finished updating your tasks.");
        int TaskNumber = UserInput.nextInt();
        UserInput.nextLine(); 

        while (TaskNumber != -1) {
            if (TaskNumber > 0 && TaskNumber <= tasks.length) {
                System.out.println("Please enter the new content for Task " + TaskNumber + " :");
                String UpdateTask = UserInput.nextLine();
                tasks[TaskNumber - 1] = UpdateTask;
                System.out.println("No. " + TaskNumber + " Task updated successfully!");
            }

            System.out.println("The following are your current tasks:");
            for (int i = 0; i < tasks.length; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }

            System.out.println("Please enter the listed number of the task you want to update:");
            System.out.println("Type \"-1\" if you have finished updating your tasks.");
            TaskNumber = UserInput.nextInt();
            UserInput.nextLine();
        }

        return tasks;
    }

    public String[] DeleteTask(String[] tasks) {
        System.out.println("The following are your current tasks:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }

        System.out.println("Please enter the listed number of the task you want to delete:");
        System.out.println("Type \"-1\" if you have finished deleting tasks.");
        int TaskNumber = UserInput.nextInt();
        UserInput.nextLine(); 

        while (TaskNumber != -1) {
            if (TaskNumber > 0 && TaskNumber <= tasks.length) {
                String[] newtasks = new String[tasks.length - 1];
                for (int i = 0, j = 0; i < tasks.length; i++) {
                    if (i != TaskNumber - 1) {
                        newtasks[j] = tasks[i];
                        j++;
                    }
                }
                tasks = newtasks;
                System.out.println("No. " + TaskNumber + " Task deleted successfully!");
            }

            System.out.println("The following are your current tasks:");
            for (int i = 0; i < tasks.length; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }

            System.out.println("Please enter the listed number of the task you want to delete:");
            System.out.println("Type \"-1\" if you have finished deleting tasks.");
            TaskNumber = UserInput.nextInt();
            UserInput.nextLine();
        }

        return tasks;
    }

    public void SearchTask(String[] tasks) {
        System.out.println("Please enter a keyword to search for in your daily tasks:");
        String keyword = UserInput.nextLine();
        boolean found = false;

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].contains(keyword)) {
                System.out.println("Task Found. No. " + (i + 1) + ". " + tasks[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found containing the keyword: " + keyword);
        }
    }

    public void ReOrder(String[] tasks) {
        System.out.println("Would you like to see all the tasks in increasing or decreasing order?");
        System.out.println("Please press 1 for increasing order. Press 2 for decreasing order.");
        String[] temp = tasks;
        int order = UserInput.nextInt();

        if (order == 1) {
            Arrays.sort(temp);
            System.out.println("Tasks in increasing order:");
            for (int i = 0; i < temp.length; i++) {
                System.out.println(temp[i]);
            }
        } else {
            Arrays.sort(temp);
            System.out.println("Tasks in decreasing order:");
            for (int i = temp.length - 1; i >= 0; i--) {
                System.out.println(temp[i]);
            }
        }
    }

    public void checkRepeated(String[] tasks) {
        boolean[] repeated = new boolean[tasks.length];
        String repeat = "";

        for (int i = 0; i < tasks.length; i++) {
            if (repeated[i]) {
                continue;
            }
            for (int j = i + 1; j < tasks.length; j++) {
                if (tasks[i].equals(tasks[j])) {
                    repeated[j] = true;
                    if (!repeat.contains(tasks[i])) {
                        repeat = repeat + tasks[i] + ".";
                    }
                }
            }
        }

        if (!repeat.isEmpty()) {
            System.out.println("Repeated task found: " + repeat);
        } else {
            System.out.println("No repeated tasks found.");
        }
    }
}



