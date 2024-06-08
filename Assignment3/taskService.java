package Assignment3;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class taskService {
	
	private Scanner userInput;
    private taskDao dao;
    
    public taskService(int size) {
    	userInput = new Scanner(System.in);
    	dao = new taskDao(size);
    }
	
	public void greeting() {
        System.out.println("Hi, Please enter your name:");
        String userName = userInput.nextLine();
        System.out.println("Hi " + userName + " :)");
	}
	
	public int numberTasks() {
        System.out.println("How many tasks would you like to accomplish today?");
        int number = userInput.nextInt();
        userInput.nextLine();
        return number;
    }
	
	public String[] getTask(int numberTasks) {
        System.out.println("Please type your " + numberTasks + " tasks for today, each task should end with a period:");
        String temp = userInput.nextLine();
        String[] tasks = temp.split("\\.");
        return tasks;
    }
	
	private void showTasks() {
        task[] tasks = dao.getTasks();
		for (task t : tasks) {
            System.out.println("ID: " + t.getTaskId() + " " + t.getTaskTitle());
        }
	}
	
	private task getTaskById(int taskId) {
        return dao.getTaskById(taskId);
    }
	
	public void addTask() {
		System.out.println("Would you like to add a new task to your daily tasks?");
        System.out.println("Type \"yes\" or \"Yes\" if you want to.");
        System.out.println("Type \"finish\" or \"Finish\" if you have finished adding your tasks.");
        String userDecision = userInput.nextLine();
        
        while (!userDecision.equalsIgnoreCase("finish")) {
            if (userDecision.equalsIgnoreCase("yes")) {
                System.out.println("Please type your new task title below.");
                String newTaskTitle = userInput.nextLine();
                
                System.out.println("Please type your new task text below.");
                String newTaskText = userInput.nextLine();
                
                System.out.println("Please type who the task is assigned to below.");
                String assignedTo = userInput.nextLine();
                
                dao.addTask(newTaskTitle, newTaskText, assignedTo);
            
                System.out.println("Would you like to add a new task to your daily tasks?");
                System.out.println("Type \"yes\" or \"Yes\" if you want to.");
                System.out.println("Type \"finish\" or \"Finish\" if you have finished adding your tasks.");
                userDecision = userInput.nextLine();
            }
        }
    }
	
	public void updateTask() {
        System.out.println("The following are your current tasks:");
        showTasks();

        System.out.println("Please enter the task ID of the task you want to update:");
        System.out.println("Type \"-1\" if you have finished updating your tasks.");
        int taskId = userInput.nextInt();
        userInput.nextLine(); 

        while (taskId != -1) {
            task updateTask = getTaskById(taskId);
            if (updateTask != null) {
                System.out.println("Please enter the new task title for Task " + taskId + " :");
                String newTaskTitle = userInput.nextLine();
                System.out.println("Please enter the new task text for Task " + taskId + " :");
                String newTaskText = userInput.nextLine();
                System.out.println("Please enter the new assigned to for Task " + taskId + " :");
                String assignedTo = userInput.nextLine();
                dao.updateTask(taskId, newTaskTitle, newTaskText, assignedTo);
                System.out.println("No. " + taskId + " Task updated successfully!");
            } else {
                System.out.println("Task not found.");
            }

            System.out.println("The following are your current tasks:");
            showTasks();

            System.out.println("Please enter the task ID of the task you want to update:");
            System.out.println("Type \"-1\" if you have finished updating your tasks.");
            taskId = userInput.nextInt();
            userInput.nextLine();
        }
    }
	
	public void deleteTask() {
        System.out.println("The following are your current tasks:");
        showTasks();
        
        System.out.println("Please enter the task ID of the task you want to delete:");
        System.out.println("Type \"-1\" if you have finished deleting tasks.");
        int taskId = userInput.nextInt();
        userInput.nextLine();
        
        while (taskId != -1) {
            if (dao.deleteTask(taskId)) {
                System.out.println("No. " + taskId + " Task deleted successfully!");
            } else {
                System.out.println("Task not found.");
            }

            System.out.println("The following are your current tasks:");
            showTasks();

            System.out.println("Please enter the task ID of the task you want to delete:");
            System.out.println("Type \"-1\" if you have finished deleting tasks.");
            taskId = userInput.nextInt();
            userInput.nextLine();
        }
	}
	
	public void searchTask() {
        System.out.println("Please enter a keyword to search for in your daily tasks:");
        String keyword = userInput.nextLine();
        boolean found = false;

        task[] tasks = dao.getTasks();
        for (task t : tasks) {
            if (t.getTaskTitle().contains(keyword) || t.getTaskText().contains(keyword)) {
                System.out.println("Task Found. No. " + t.getTaskId() + " Task: " + t.getTaskTitle());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found containing the keyword: " + keyword);
        }
    }
	
	public void reorderTask() {
        System.out.println("Would you like to see all the tasks in increasing or decreasing order?");
        System.out.println("Please press 1 for increasing order. Press 2 for decreasing order.");
        int order = userInput.nextInt();
        userInput.nextLine();
        
        task[] tasks = dao.getTasks();
        Arrays.sort(tasks, new Comparator<task>() {
            @Override
            public int compare(task t1, task t2) {
                return t1.getTaskTitle().compareToIgnoreCase(t2.getTaskTitle());
            }
        });
        
        if (order == 1) {
            System.out.println("Tasks in increasing order:");
            for (task t : tasks) {
                System.out.println("ID: " + t.getTaskId() + " " + t.getTaskTitle());
            }
        } else if (order == 2) {
            System.out.println("Tasks in decreasing order:");
            for (int i = tasks.length - 1; i >= 0; i--) {
                System.out.println("ID: " + tasks[i].getTaskId() + " " + tasks[i].getTaskTitle());
            }
        } else {
            System.out.println("Invalid input. Please press 1 for increasing order or 2 for decreasing order.");
        }
	}
	
	public void checkRepeated() {
        boolean[] repeated = new boolean[dao.getTasks().length];
        String repeat = "";
        task[] tasks = dao.getTasks();
        
        for (int i = 0; i < tasks.length; i++) {
            if (repeated[i]) {
                continue;
            }
            for (int j = i + 1; j < tasks.length; j++) {
                if (tasks[i].getTaskTitle().equals(tasks[j].getTaskTitle())) {
                    repeated[j] = true;
                    if (!repeat.contains(tasks[i].getTaskTitle())) {
                        repeat = repeat + tasks[i].getTaskTitle() + ".";
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


