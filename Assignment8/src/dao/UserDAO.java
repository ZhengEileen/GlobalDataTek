package dao;

import pojo.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private List<User> users = new ArrayList<>();

	// Create
	public boolean addUser(User user) {
		return users.add(user);
	}

	// Read
	public User findUser(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	// Update
	public boolean updateUser(User user) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(user.getUsername())) {
				users.set(i, user);
				return true;
			}
		}
		return false;
	}

	// Delete
	public boolean deleteUser(String username) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				users.remove(i);
				return true;
			}
		}
		return false;
	}
}

