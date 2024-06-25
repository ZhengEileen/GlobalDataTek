package controller;

import service.TaskService;
import service.UserService;
import dao.TaskDAO;
import dao.UserDAO;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		TaskDAO taskDAO = new TaskDAO();
		UserService userService = new UserService(userDAO);
		TaskService taskService = new TaskService(taskDAO);
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("Welcome to the Todo Manager Application!");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("0. Exit");
			System.out.print("Please enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); 

			if (choice == 0) {
				System.out.println("Thank you for using the Todo Manager Application. Goodbye!");
				exit = true;
			} else if (choice == 1) {
				userService.registerUser();
			} else if (choice == 2) {
				userService.loginUser();
				if (userService.getLoggedInUser() != null) {
					if ("client".equalsIgnoreCase(userService.getLoggedInUser().getRole())) {
						new Thread(new ClientSession(userService, taskService)).start();
					} else if ("visitor".equalsIgnoreCase(userService.getLoggedInUser().getRole())) {
						new Thread(new VisitorSession(userService, taskService)).start();
					}
				}
			} else {
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}


