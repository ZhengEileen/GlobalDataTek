package service;

import java.util.Scanner;
import dao.TaskDAO;
import pojo.Task;

public class TaskService {

	private TaskDAO taskDAO = new TaskDAO();
	private Scanner sc = new Scanner(System.in);

	// Save Task
	public void saveTask() {
		System.out.println("Enter the Task Id:");
		int taskId = sc.nextInt();
		sc.nextLine();

		System.out.println("Enter the Task Name:");
		String taskName = sc.nextLine();

		System.out.println("Enter the Labour Amount:");
		int labourAmount = sc.nextInt();

		Task task = new Task(taskId, taskName, labourAmount);
		taskDAO.addTask(task);
		System.out.println("Task added successfully.");
	}

	// Delete Task
	public void deleteTask() {
		System.out.println("Enter the Task Id to delete the Task:");
		int deleteTaskId = sc.nextInt();

		Task task = taskDAO.findTask(deleteTaskId);

		if (task != null) {
			if (task.getLabourAmount() > 100 && task.getTaskId() > 101) {
				boolean deleteStatus = taskDAO.deleteTask(deleteTaskId);
				if (deleteStatus) {
					System.out.println("Deleted Successfully...");
				} else {
					System.out.println("Task deletion failed...");
				}
			} else {
				System.out.println("Task cannot be deleted due to business rules...");
			}
		} else {
			System.out.println("Could not find the Task by the Task Id provided...");
		}
	}

	// Update Task
	public void updateTask() {
		System.out.println("Enter the Task Id to update the Task:");
		int taskId = sc.nextInt();
		sc.nextLine(); // Consume newline

		Task existingTask = taskDAO.findTask(taskId);
		if (existingTask != null) {
			System.out.println("Enter the new Task Name:");
			String taskName = sc.nextLine();

			System.out.println("Enter the new Labour Amount:");
			int labourAmount = sc.nextInt();

			Task updatedTask = new Task(taskId, taskName, labourAmount);
			boolean updateStatus = taskDAO.updateTask(updatedTask);

			if (updateStatus) {
				System.out.println("Task updated successfully.");
			} else {
				System.out.println("Task update failed.");
			}
		} else {
			System.out.println("Could not find the Task by the Task Id provided...");
		}
	}

	// Find Task
	public void findTask() {
		System.out.println("Enter the Task Id to find the Task:");
		int taskId = sc.nextInt();

		Task task = taskDAO.findTask(taskId);
		if (task != null) {
			System.out.println("Task found: " + task);
		} else {
			System.out.println("Could not find the Task by the Task Id provided...");
		}
	}
}
