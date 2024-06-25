package service;

import pojo.User;
import dao.UserDAO;
import java.util.Scanner;

public class UserService {

	private UserDAO userDAO;
	private User loggedInUser;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void registerUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your user ID:");
		int userId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.println("Please enter your username:");
		String username = scanner.nextLine();
		System.out.println("Please enter your password:");
		String password = scanner.nextLine();
		System.out.println("Please enter your role (client/visitor):");
		String role = scanner.nextLine();

		if ("visitor".equalsIgnoreCase(role)) {
			System.out.println("Sorry, visitors cannot register themselves. The program will exit.");
			System.exit(0);
		}

		User user = new User(userId, username, password, role);
		boolean success = userDAO.addUser(user);
		if (success) {
			System.out.println("Registration successful.");
		} else {
			System.out.println("Registration failed.");
		}
	}

	public void loginUser() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Please enter your username:");
			String username = scanner.nextLine();
			System.out.println("Please enter your password:");
			String password = scanner.nextLine();

			User user = userDAO.findUser(username, password);
			if (user != null) {
				loggedInUser = user;
				System.out.println("Login successful. Welcome, " + loggedInUser.getUsername() + "!");
				break;
			} else {
				System.out.println("Login failed. Please check your username and password.");
				System.out.println("Enter 0 to exit or any other key to try again.");
				String choice = scanner.nextLine();
				if ("0".equals(choice)) {
					System.exit(0);
				}
			}
		}
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void registerVisitor() {
		if (!"client".equalsIgnoreCase(loggedInUser.getRole())) {
			System.out.println("Only clients can register visitors.");
			return;
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the visitor's user ID:");
		int userId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.println("Please enter the visitor's username:");
		String username = scanner.nextLine();
		System.out.println("Please enter the visitor's password:");
		String password = scanner.nextLine();

		User visitor = new User(userId, username, password, "visitor");
		boolean success = userDAO.addUser(visitor);
		if (success) {
			System.out.println("Visitor registration successful.");
		} else {
			System.out.println("Visitor registration failed.");
		}
	}
}

