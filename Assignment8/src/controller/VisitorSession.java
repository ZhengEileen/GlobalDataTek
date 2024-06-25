package controller;

import service.TaskService;
import service.UserService;

import java.util.Scanner;

public class VisitorSession implements Runnable {

	private UserService userService;
	private TaskService taskService;

	public VisitorSession(UserService userService, TaskService taskService) {
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
			System.out.println("2. Mark Task as Complete");
			System.out.println("3. View Completed Tasks");
			System.out.println("4. View Incomplete Tasks");
			System.out.println("5. Arrange My Tasks in Increasing Order");
			System.out.println("6. Arrange My Tasks in Decreasing Order");
			System.out.println("0. Logout");
			System.out.print("Please enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			if (choice == 0) {
				System.out.println("Logging out. Thank you for using the Todo Manager Application.");
				logout = true;
			} else if (choice == 1) {
				taskService.assignedTo(username);
			} else if (choice == 2) {
				taskService.markTask(username);
			} else if (choice == 3) {
				taskService.getCompletionTask(username);
			} else if (choice == 4) {
				taskService.getIncompleteTask(username);
			} else if (choice == 5) {
				taskService.arrangeTasks(1, username);
			} else if (choice == 6) {
				taskService.arrangeTasks(0, username);
			} else {
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
