package com.infosys.dao;

import com.infosys.pojo.User;

public class UserDAO {
	private User[] users;
	private int userCount;

	public UserDAO() {
		this.users = new User[10]; // Initial size
		this.userCount = 0;
	}

	public void addUser(User user) {
		if (userCount == users.length) {
			User[] newUsers = new User[users.length * 2];
			for (int i = 0; i < users.length; i++) {
				newUsers[i] = users[i];
			}
			users = newUsers;
		}
		users[userCount++] = user;
	}

	public User getUserByUsername(String username) {
		for (int i = 0; i < userCount; i++) {
			if (users[i].getUserName().equals(username)) {
				return users[i];
			}
		}
		return null;
	}

	public User getUserById(Long userId) {
		for (int i = 0; i < userCount; i++) {
			if (users[i].getUserId().equals(userId)) {
				return users[i];
			}
		}
		return null;
	}

	public boolean verifyUser(String username, String password) {
		User user = getUserByUsername(username);
		return user != null && user.getPassword().equals(password);
	}

	public void updateUser(User updatedUser) {
		for (int i = 0; i < userCount; i++) {
			if (users[i].getUserName().equals(updatedUser.getUserName())) {
				users[i] = updatedUser;
				return;
			}
		}
		System.out.println("User not found.");
	}

	public void deleteUser(String username) {
		for (int i = 0; i < userCount; i++) {
			if (users[i].getUserName().equals(username)) {
				users[i] = users[--userCount];
				users[userCount] = null;
				return;
			}
		}
		System.out.println("User not found.");
	}
}

