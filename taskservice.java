package Assignment3;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class taskservice {
	
	private Scanner UserInput;
    private daotask Dao;
    
    public taskservice(int size) {
    	UserInput = new Scanner(System.in);
    	Dao = new daotask(size);
    }
	
	public void Greeting() {
        System.out.println("Hi, Please enter your name:");
        String UserName = UserInput.nextLine();
        System.out.println("Hi " + UserName + " :)");
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
	
	private void ShowTask() {
        task[] tasks = Dao.GetTasks();
		for (task t : tasks) {
            System.out.println("ID: " + t.GetTaskId() + " " + t.GetTaskTitle());
        }
	}
	
	private task GetTaskById(int TaskId) {
        return Dao.GetTaskById(TaskId);
    }
	
	public void AddTask() {
		System.out.println("Would you like to add a new task to your daily tasks?");
        System.out.println("Type \"yes\" or \"Yes\" if you want to.");
        System.out.println("Type \"finish\" or \"Finish\" if you have finished adding your tasks.");
        String UserDecision = UserInput.nextLine();
        
        while (!UserDecision.equalsIgnoreCase("finish")) {
            if (UserDecision.equalsIgnoreCase("yes")) {
                System.out.println("Please type your new task title below.");
                String NewTaskTitle = UserInput.nextLine();
                
                System.out.println("Please type your new task text below.");
                String NewTaskText = UserInput.nextLine();
                
                System.out.println("Please type who the task is assigned to below.");
                String assignedTo = UserInput.nextLine();
                
                Dao.AddTask(NewTaskTitle, NewTaskText, assignedTo);
            
                System.out.println("Would you like to add a new task to your daily tasks?");
                System.out.println("Type \"yes\" or \"Yes\" if you want to.");
                System.out.println("Type \"finish\" or \"Finish\" if you have finished adding your tasks.");
                UserDecision = UserInput.nextLine();
            }
        }
    }
	
	public void UpdateTask() {
        System.out.println("The following are your current tasks:");
        ShowTask();

        System.out.println("Please enter the task ID of the task you want to update:");
        System.out.println("Type \"-1\" if you have finished updating your tasks.");
        int TaskId = UserInput.nextInt();
        UserInput.nextLine(); 

        while (TaskId != -1) {
            task UpdateTask = GetTaskById(TaskId);
            if (UpdateTask != null) {
                System.out.println("Please enter the new task title for Task " + TaskId + " :");
                String NewTaskTitle = UserInput.nextLine();
                System.out.println("Please enter the new task text for Task " + TaskId + " :");
                String NewTaskText = UserInput.nextLine();
                System.out.println("Please enter the new assigned to for Task " + TaskId + " :");
                String assignedTo = UserInput.nextLine();
                Dao.UpdateTask(TaskId, NewTaskTitle, NewTaskText, assignedTo);
                System.out.println("No. " + TaskId + " Task updated successfully!");
            } else {
                System.out.println("Task not found.");
            }

            System.out.println("The following are your current tasks:");
            ShowTask();

            System.out.println("Please enter the task ID of the task you want to update:");
            System.out.println("Type \"-1\" if you have finished updating your tasks.");
            TaskId = UserInput.nextInt();
            UserInput.nextLine();
        }
    }
	
	public void DeleteTask() {
        System.out.println("The following are your current tasks:");
        ShowTask();
        
        System.out.println("Please enter the task ID of the task you want to delete:");
        System.out.println("Type \"-1\" if you have finished deleting tasks.");
        int TaskId = UserInput.nextInt();
        UserInput.nextLine();
        
        while (TaskId != -1) {
            if (Dao.DeleteTask(TaskId)) {
                System.out.println("No. " + TaskId + " Task deleted successfully!");
            } else {
                System.out.println("Task not found.");
            }

            System.out.println("The following are your current tasks:");
            ShowTask();

            System.out.println("Please enter the task ID of the task you want to delete:");
            System.out.println("Type \"-1\" if you have finished deleting tasks.");
            TaskId = UserInput.nextInt();
            UserInput.nextLine();
        }
	}
	
	public void SearchTask() {
        System.out.println("Please enter a keyword to search for in your daily tasks:");
        String Keyword = UserInput.nextLine();
        boolean found = false;

        task[] tasks = Dao.GetTasks();
        for (task t : tasks) {
            if (t.GetTaskTitle().contains(Keyword) || t.GetTaskText().contains(Keyword)) {
                System.out.println("Task Found. No. " + t.GetTaskId() + " Task: " + t.GetTaskTitle());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found containing the keyword: " + Keyword);
        }
    }
	
	public void ReorderTask() {
        System.out.println("Would you like to see all the tasks in increasing or decreasing order?");
        System.out.println("Please press 1 for increasing order. Press 2 for decreasing order.");
        int Order = UserInput.nextInt();
        UserInput.nextLine();
        
        task[] tasks = Dao.GetTasks();
        Arrays.sort(tasks, new Comparator<task>() {
            @Override
            public int compare(task t1, task t2) {
                return t1.GetTaskTitle().compareToIgnoreCase(t2.GetTaskTitle());
            }
        });
        
        if (Order == 1) {
            System.out.println("Tasks in increasing order:");
            for (task t : tasks) {
                System.out.println("ID: " + t.GetTaskId() + " " + t.GetTaskTitle());
            }
        } else if (Order == 2) {
            System.out.println("Tasks in decreasing order:");
            for (int i = tasks.length - 1; i >= 0; i--) {
                System.out.println("ID: " + tasks[i].GetTaskId() + " " + tasks[i].GetTaskTitle());
            }
        } else {
            System.out.println("Invalid input. Please press 1 for increasing order or 2 for decreasing order.");
        }
	}
	
	public void checkRepeated() {
        boolean[] Repeated = new boolean[Dao.GetTasks().length];
        String repeat = "";
        task[] tasks = Dao.GetTasks();
        
        for (int i = 0; i < tasks.length; i++) {
            if (Repeated[i]) {
                continue;
            }
            for (int j = i + 1; j < tasks.length; j++) {
                if (tasks[i].GetTaskTitle().equals(tasks[j].GetTaskTitle())) {
                    Repeated[j] = true;
                    if (!repeat.contains(tasks[i].GetTaskTitle())) {
                        repeat = repeat + tasks[i].GetTaskTitle() + ".";
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
