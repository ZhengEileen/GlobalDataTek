package com.infosys.dao;

import com.infosys.pojo.User;
import com.infosys.exception.UserLimitException;

public class UserDao {

    private User[] users;
    private int userCount;

    public UserDao() {
        users = new User[10];
        userCount = 0;
    }

    public void addUser(User user) throws UserLimitException {
        if (userCount >= users.length) {
            throw new UserLimitException("User limit exceeded. Cannot add more users.");
        } else {
            users[userCount++] = user;
        }
    }

    public User getUser(String userName) {
        for (User value : users) {
            if (value != null && value.getUserName().equals(userName)) {
                return value;
            }
        }
        return null;
    }

    public void updateUser(User newUser) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUserName().equals(newUser.getUserName())) {
                users[i] = newUser;
                break;
            }
        }
    }

    public void deleteUser(User oldUser) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUserName().equals(oldUser.getUserName())) {
                for (int j = i; j < userCount - 1; j++) {
                    users[j] = users[j + 1];
                }
                users[userCount - 1] = null;
                userCount--;
                break;
            }
        }
    }
    
    public int getUserCount() {
        return userCount;
    }
}
