package service;

import pojo.Task;
import dao.TaskDAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TaskService {

	private TaskDAO taskDAO;

	public TaskService(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	public void addTask() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the task ID:");
		int taskId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Please enter the task title:");
		String taskTitle = scanner.nextLine();
		System.out.println("Please enter the task text:");
		String taskText = scanner.nextLine();
		System.out.println("Please enter the assignee:");
		String assignedTo = scanner.nextLine();

		Task task = new Task(taskId, taskTitle, taskText, assignedTo, false);
		taskDAO.addTask(task);
		System.out.println("Task has been added successfully.");
	}

	public void findTask() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the task ID to search for:");
		int taskId = scanner.nextInt();

		Task task = taskDAO.findTask(taskId);
		if (task != null) {
			System.out.println("The task has been found: " + taskToString(task));
		} else {
			System.out.println("Unfortunately, the task could not be found.");
		}
	}

	public void updateTask() {
		showAllTasks();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the task ID to update:");
		int taskId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Please enter the new task title:");
		String taskTitle = scanner.nextLine();
		System.out.println("Please enter the new task text:");
		String taskText = scanner.nextLine();
		System.out.println("Please enter the new assignee:");
		String assignedTo = scanner.nextLine();

		Task updatedTask = new Task(taskId, taskTitle, taskText, assignedTo, false);
		boolean success = taskDAO.updateTask(updatedTask);

		if (success) {
			System.out.println("The task has been updated successfully.");
		} else {
			System.out.println("The task update failed.");
		}
	}

	public void deleteTask() {
		showAllTasks();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the task ID you want to delete:");
		int taskId = scanner.nextInt();

		boolean success = taskDAO.deleteTask(taskId);
		if (success) {
			System.out.println("The task has been deleted successfully.");
		} else {
			System.out.println("The task deletion failed.");
		}
	}

	public void searchTask() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the text to search for in tasks:");
		String searchText = scanner.nextLine();

		List<Task> result = new ArrayList<>();
		for (Task task : taskDAO.getAllTasks()) {
			if (task.getTaskTitle().contains(searchText) || task.getTaskText().contains(searchText)) {
				result.add(task);
			}
		}

		System.out.println("Search results:");
		for (Task task : result) {
			System.out.println(taskToString(task));
		}
	}

	public void assignTask() {
		showAllTasks();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the task ID you want to assign:");
		int taskId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Please enter the assignee username:");
		String assignedTo = scanner.nextLine();

		Task task = taskDAO.findTask(taskId);
		if (task != null) {
			task.setAssignedTo(assignedTo);
			taskDAO.updateTask(task);
			System.out.println("The task has been assigned successfully.");
		} else {
			System.out.println("Unfortunately, the task could not be found.");
		}
	}

	public void arrangeTasks(int order, String username) {
		List<Task> tasks = new ArrayList<>(taskDAO.getAllTasks());
		if (username != null) {
			tasks.removeIf(task -> !task.getAssignedTo().equals(username));
		}

		if (order == 1) {
			tasks.sort(Comparator.comparingInt(Task::getTaskId));
			System.out.println("Tasks arranged in increasing order:");
		} else {
			tasks.sort(Comparator.comparingInt(Task::getTaskId).reversed());
			System.out.println("Tasks arranged in decreasing order:");
		}

		for (Task task : tasks) {
			System.out.println(taskToString(task));
		}
	}

	public void markTask(String username) {
		assignedTo(username);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the task ID to mark as complete:");
		int taskId = scanner.nextInt();

		Task task = taskDAO.findTask(taskId);
		if (task != null) {
			task.setComplete(true);
			taskDAO.updateTask(task);
			System.out.println("The task has been marked as complete.");
		} else {
			System.out.println("Unfortunately, the task could not be found.");
		}
	}

	public void getCompletionTask(String username) {
		List<Task> result = new ArrayList<>();
		for (Task task : taskDAO.getAllTasks()) {
			if (task.isComplete() && task.getAssignedTo().equals(username)) {
				result.add(task);
			}
		}

		System.out.println("Completed tasks:");
		for (Task task : result) {
			System.out.println(taskToString(task));
		}
	}

	public void getIncompleteTask(String username) {
		List<Task> result = new ArrayList<>();
		for (Task task : taskDAO.getAllTasks()) {
			if (!task.isComplete() && task.getAssignedTo().equals(username)) {
				result.add(task);
			}
		}

		System.out.println("Incomplete tasks:");
		for (Task task : result) {
			System.out.println(taskToString(task));
		}
	}

	public void assignedTo(String username) {
		List<Task> result = new ArrayList<>();
		for (Task task : taskDAO.getAllTasks()) {
			if (task.getAssignedTo().equals(username)) {
				result.add(task);
			}
		}

		if (result.isEmpty()) {
			System.out.println("Invalid username. No tasks assigned to " + username + ".");
		} else {
			System.out.println("Tasks assigned to you (" + username + "):");
			for (Task task : result) {
				System.out.println(taskToString(task));
			}
		}
	}

	public void showAllTasks() {
		List<Task> tasks = taskDAO.getAllTasks();
		System.out.println("Listed below are your current tasks:");
		for (Task task : tasks) {
			System.out.println(taskToString(task));
		}
	}

	private String taskToString(Task task) {
		return "Task ID: " + task.getTaskId() + ", Title: " + task.getTaskTitle() + ", Text: " + task.getTaskText()
				+ ", Assigned To: " + task.getAssignedTo() + ", Completed: " + (task.isComplete() ? "Yes" : "No");
	}
}




