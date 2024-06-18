package service;

import java.util.Scanner;
import dao.UserDAO;
import pojo.User;

public class UserService {

	private Scanner sc = new Scanner(System.in);
	private UserDAO userDAO = new UserDAO();

	// Register a new user
	public void register() {
		System.out.println("Enter the Username:");
		String username = sc.nextLine();

		System.out.println("Enter the Password:");
		String password = sc.nextLine();

		System.out.println("Enter the Name:");
		String name = sc.nextLine();

		System.out.println("Enter the Role:");
		String role = sc.nextLine();

		User user = new User(username, password, name, role);
		boolean status = userDAO.addUser(user);

		if (status) {
			System.out.println("User registered successfully.");
		} else {
			System.out.println("User registration failed.");
		}
	}

	// Login a user
	public void login() {
		System.out.println("Enter the Username:");
		String username = sc.nextLine();

		System.out.println("Enter the Password:");
		String password = sc.nextLine();

		User user = userDAO.findUser(username, password);

		if (user != null) {
			if (user.getRole().equalsIgnoreCase("Client")) {
				System.out.println("You can display the complete menu.");
			} else {
				System.out.println("You can display the options to the visitor.");
			}
		} else {
			System.out.println("User not found.");
		}
	}
}
