package dao;

import pojo.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

	private List<Task> tasks = new ArrayList<>();

	// Create
	public void addTask(Task task) {
		tasks.add(task);
	}

	// Read
	public Task findTask(int taskId) {
		for (Task task : tasks) {
			if (task.getTaskId() == taskId) {
				return task;
			}
		}
		return null;
	}

	// Update
	public boolean updateTask(Task updatedTask) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskId() == updatedTask.getTaskId()) {
				tasks.set(i, updatedTask);
				return true;
			}
		}
		return false;
	}

	// Delete
	public boolean deleteTask(int taskId) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskId() == taskId) {
				tasks.remove(i);
				return true;
			}
		}
		return false;
	}
}
