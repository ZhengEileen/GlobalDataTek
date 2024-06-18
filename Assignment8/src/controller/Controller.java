package controller;

import java.util.Scanner;

import service.TaskService;
import service.UserService;

public class Controller {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		boolean flag = true;

		TaskService taskService = new TaskService();
		UserService userService = new UserService();

		while (flag) {

			System.out.println("Enter 1 for Add Task \nEnter 2 for Delete Task \nEnter 3 for Registration \nEnter 4 for Login \nEnter 0 for Exit");

			int mainMenuInput = sc.nextInt();

			switch (mainMenuInput) {
			case 1:

				taskService.saveTask();

				break;

			case 2:

				taskService.deleteTask();

				break;

			case 3:

				userService.register();

				break;

			case 4:

				userService.login();

				break;

			case 0:

				flag = false;
				break;
			}
		}
	}
}
