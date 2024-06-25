package controller;

import service.TaskService;
import service.UserService;

import java.util.Scanner;

public class ClientSession implements Runnable {

	private UserService userService;
	private TaskService taskService;

	public ClientSession(UserService userService, TaskService taskService) {
		this.userService = userService;
		this.taskService = taskService;
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		String username = userService.getLoggedInUser().getUsername();
		boolean logout = false;

		while (!logout) {
			System.out.println("\nHere are the operations you can perform:");
			System.out.println("1. View Tasks");
			System.out.println("2. Add Task");
			System.out.println("3. Update Task");
			System.out.println("4. Delete Task");
			System.out.println("5. Search Task");
			System.out.println("6. Assign Task");
			System.out.println("7. Register the Visitor Account");
			System.out.println("8. Arrange Tasks in Increasing Order");
			System.out.println("9. Arrange Tasks in Decreasing Order");
			System.out.println("0. Logout");
			System.out.print("Please enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			if (choice == 0) {
				System.out.println("Logging out. Thank you for using the Todo Manager Application.");
				logout = true;
			} else if (choice == 1) {
				taskService.showAllTasks();
			} else if (choice == 2) {
				taskService.addTask();
			} else if (choice == 3) {
				taskService.updateTask();
			} else if (choice == 4) {
				taskService.deleteTask();
			} else if (choice == 5) {
				taskService.searchTask();
			} else if (choice == 6) {
				taskService.assignTask();
			} else if (choice == 7) {
				userService.registerVisitor();
			} else if (choice == 8) {
				taskService.arrangeTasks(1, null);
			} else if (choice == 9) {
				taskService.arrangeTasks(0, null);
			} else {
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
