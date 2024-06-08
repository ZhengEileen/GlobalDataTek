package Assignment4;


public class UserDao {
	
	private User[] users;
	private int userCount;
	
	public UserDao() {
		users = new User[5]; 
		userCount = 0;
	}

	
	public void addUser(User user){
		if (userCount < users.length){
			users[userCount] = user;
			userCount++;
		}
		
	}
	
	public User getUser(String userName) {
		for(User user: users) {
			if(user != null && user.getUserName().equals(userName)) {
				return user;
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
		for(int i = 0; i< userCount; i++) {
			if(users[i].getUserName().equals(oldUser.getUserName())) {
				for (int j = i; j < userCount-1; j++) {
					users[j] = users[j + 1];
				}
				users[userCount-1] = null;
				userCount--;
				break; 
			}
		}
	}
	

}
