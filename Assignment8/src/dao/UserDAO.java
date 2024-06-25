package dao;

import pojo.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private List<User> users = new ArrayList<>();

	public boolean addUser(User user) {
		return users.add(user);
	}

	public User findUser(String username, String password) {
		User findUser = null;
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				findUser = user;
			}
		}
		return findUser;
	}

	public boolean updateUser(User user) {
		boolean isUpdate = false;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(user.getUsername())) {
				users.set(i, user);
				isUpdate = true;
			}
		}
		return isUpdate;
	}

	public boolean deleteUser(String username) {
		boolean isDelete = false;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				users.remove(i);
				isDelete = true;
			}
		}
		return isDelete;
	}
}

