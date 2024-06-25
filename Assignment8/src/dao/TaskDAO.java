package dao;

import pojo.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

	private List<Task> tasks = new ArrayList<>();

	public void addTask(Task task) {
		tasks.add(task);
	}

	public Task findTask(int taskId) {
		Task findTask = null;
		for (Task task : tasks) {
			if (task.getTaskId() == taskId) {
				findTask = task;
			}
		}
		return findTask;
	}

	public List<Task> getAllTasks() {
		List<Task> allTasks = new ArrayList<>();
		for (Task task : tasks) {
			allTasks.add(task);
		}
		return allTasks;
	}

	public boolean updateTask(Task updatedTask) {
		boolean isUpdate = false;
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskId() == updatedTask.getTaskId()) {
				tasks.set(i, updatedTask);
				isUpdate = true;
			}
		}
		return isUpdate;
	}

	public boolean deleteTask(int taskId) {
		boolean isDelete = false;
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskId() == taskId) {
				tasks.remove(i);
				isDelete = true;
			}
		}
		return isDelete;
	}
}
