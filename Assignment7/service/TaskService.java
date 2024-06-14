package com.infosys.service;

import com.infosys.dao.TaskDao;
import com.infosys.exception.TaskLimitException;
import com.infosys.pojo.Task;
import com.infosys.pojo.User;

import java.util.Scanner;
import java.util.Random;

public class TaskService {

	private Scanner userInput;
	private TaskDao dao;
	private User currentUser;
	private boolean[] usedIds;

	public TaskService() {
		userInput = new Scanner(System.in);
		usedIds = new boolean[100];
		dao = new TaskDao();
	}

	public TaskService(int size, User currentUser) {
		userInput = new Scanner(System.in);
		dao = new TaskDao();
		this.currentUser = currentUser;
	}

	public int generateTaskId() {
		Random randomNum = new Random();
		int taskId = 10 + randomNum.nextInt(90);
		while (usedIds[taskId]) {
			taskId = 10 + randomNum.nextInt(90);
		}
		usedIds[taskId] = true;
		return taskId;
	}

	public void addTask() throws TaskLimitException {
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

				System.out.println("Please type the username of the person the task is assigned to below.");
				String assignedTo = userInput.nextLine();

				int taskId = generateTaskId();
				dao.addTask(taskId, newTaskTitle, newTaskText, assignedTo);
				System.out.println("Task added successfully with ID: " + taskId);

				System.out.println("Would you like to add a new task to your daily tasks?");
				System.out.println("Type \"yes\" or \"Yes\" if you want to.");
				System.out.println("Type \"finish\" or \"Finish\" if you have finished adding your tasks.");
				userDecision = userInput.nextLine();
			}
		}
	}

	public void showTasks() {
		Task[] tasks = dao.getTasks();
		for (Task t : tasks) {
			if (t != null) {
				System.out.println("ID: " + t.getTaskId() + " " + t.getTaskTitle());
			}
		}
	}

	public Task getTaskById(int taskId) {
		return dao.getTaskById(taskId);
	}

	public void updateTask() {
		System.out.println("The following are your current tasks:");
		showTasks();

		System.out.println("Please enter the task ID of the task you want to update:");
		System.out.println("Type \"-1\" if you have finished updating your tasks.");
		int taskId = userInput.nextInt();
		userInput.nextLine();

		while (taskId != -1) {
			Task updateTask = getTaskById(taskId);
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

		Task[] tasks = dao.getTasks();
		for (Task t : tasks) {
			if (t != null && (t.getTaskTitle().contains(keyword) || t.getTaskText().contains(keyword))) {
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

		Task[] tasks = dao.getTasks();

		int taskCount = 0;

		// Count non-null tasks
		for (Task t : tasks) {
			if (t != null) {
				taskCount++;
			}
		}

		Task[] nonNullTasks = new Task[taskCount];
		int index = 0;
		for (Task task : tasks) {
			if (task != null) {
				nonNullTasks[index++] = task;
			}
		}

		if (currentUser.getRole().equals("Visitor")) {
			taskCount = 0;

			for (Task task : nonNullTasks) {
				if (task.getAssignedTo().equals(currentUser.getUserName())) {
					taskCount++;
				}
			}

			Task[] visitorTasks = new Task[taskCount];
			index = 0;
			for (Task task : nonNullTasks) {
				if (task.getAssignedTo().equals(currentUser.getUserName())) {
					visitorTasks[index] = task;
					index++;
				}
			}
			nonNullTasks = visitorTasks;
		}

		// Sort tasks by the first letter of the task title
		for (int i = 0; i < nonNullTasks.length - 1; i++) {
			for (int j = i + 1; j < nonNullTasks.length; j++) {
				if (order == 1) {
					if (nonNullTasks[i].getTaskTitle().compareToIgnoreCase(nonNullTasks[j].getTaskTitle()) > 0) {
						Task temp = nonNullTasks[i];
						nonNullTasks[i] = nonNullTasks[j];
						nonNullTasks[j] = temp;
					}
				} else if (order == 2) {
					if (nonNullTasks[i].getTaskTitle().compareToIgnoreCase(nonNullTasks[j].getTaskTitle()) < 0) {
						Task temp = nonNullTasks[i];
						nonNullTasks[i] = nonNullTasks[j];
						nonNullTasks[j] = temp;
					}
				}
			}
		}

		if (order == 1) {
			System.out.println("Tasks in increasing order:");
			for (Task t : nonNullTasks) {
				System.out.println("ID: " + t.getTaskId() + " " + t.getTaskTitle());
			}
		} else if (order == 2) {
			System.out.println("Tasks in decreasing order:");
			for (int i = nonNullTasks.length - 1; i >= 0; i--) {
				System.out.println("ID: " + nonNullTasks[i].getTaskId() + " " + nonNullTasks[i].getTaskTitle());
			}
		} else {
			System.out.println("Invalid input. Please press 1 for increasing order or 2 for decreasing order.");
		}
	}

	public void checkRepeated() {
		boolean[] repeated = new boolean[dao.getTasks().length];
		String repeat = "";
		Task[] tasks = dao.getTasks();

		for (int i = 0; i < tasks.length; i++) {
			if (tasks[i] == null)
				continue;
			if (repeated[i]) {
				continue;
			}
			for (int j = i + 1; j < tasks.length; j++) {
				if (tasks[j] == null)
					continue;
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

	public void showTasksAssignedTo(String assignedTo) {
		Task[] tasks = dao.getTasks();
		boolean found = false;
		for (Task task : tasks) {
			if (task != null && task.getAssignedTo().equals(assignedTo)) {
				System.out.println("ID: " + task.getTaskId() + " Task Title: " + task.getTaskTitle() + " Task Text: "
						+ task.getTaskText());
				found = true;
			}
		}
		if (!found) {
			System.out.println("No task found. Please contact your client.");
		}
	}

	public void showTaskStatus() {
		System.out.println("Would you like to see your task completion status?");
		System.out.println("Press 1 to show completed tasks, or press 2 to show incomplete tasks.");
		int choice = userInput.nextInt();
		userInput.nextLine();

		Task[] tasks = dao.getTasks();
		boolean found = false;

		if (choice == 1) {
			System.out.println("Here are your completed tasks:");
			for (Task task : tasks) {
				if (task != null && task.getAssignedTo().equals(currentUser.getUserName()) && task.isCompleted()) {
					System.out.println(
							"ID: " + task.getTaskId() + " Task Title: " + task.getTaskTitle() + " Status: Completed");
					found = true;
				}
			}
		} else if (choice == 2) {
			System.out.println("Here are your incomplete tasks:");
			for (Task task : tasks) {
				if (task != null && task.getAssignedTo().equals(currentUser.getUserName()) && !task.isCompleted()) {
					System.out.println("ID: " + task.getTaskId() + " Task Title: " + task.getTaskTitle()
							+ " Status: Not Completed");
					found = true;
				}
			}
		} else {
			System.out.println("Invalid choice.");
			return;
		}

		if (!found) {
			System.out.println("No tasks found for the selected status.");
		}
	}

	public void markTaskCompleted() {
		while (true) {
			System.out.println("Here are the tasks assigned to you:");
			showTasksAssignedTo(currentUser.getUserName());
			System.out.println("Please enter the task ID of the task you want to mark as completed:");
			System.out.println("Type \"-1\" if you have finished marking tasks as completed.");
			int taskId = userInput.nextInt();
			userInput.nextLine();

			if (taskId == -1) {
				break;
			}

			Task task = dao.getTaskById(taskId);
			if (task != null && task.getAssignedTo().equals(currentUser.getUserName())) {
				task.setCompleted(true);
				System.out.println("Task ID " + taskId + " marked as completed.");
			} else {
				System.out.println("Task not found.");
			}
		}
	}
}
