package com.infosys.dao;

import com.infosys.pojo.Task;
import com.infosys.exception.TaskLimitException;

public class TaskDao {

	private Task[] tasks;
	private int taskCount;

	public TaskDao() {
		tasks = new Task[100];
		taskCount = 0;
	}

	public void addTask(int taskId, String taskTitle, String taskText, String assignedTo) throws TaskLimitException {
		if (taskCount >= tasks.length) {
			throw new TaskLimitException("Task limit exceeded. Cannot add more tasks.");
		} else {
			Task newTask = new Task(taskId, taskTitle, taskText, assignedTo, false);
			tasks[taskCount++] = newTask;
		}
	}

	public Task[] getTasks() {
		return tasks;
	}

	public Task getTaskById(int taskId) {
		for (int i = 0; i < taskCount; i++) {
			if (tasks[i].getTaskId() == taskId) {
				return tasks[i];
			}
		}
		return null;
	}

	public boolean updateTask(int taskId, String taskTitle, String taskText, String assignedTo) {
		Task updateTask = getTaskById(taskId);
		if (updateTask != null) {
			updateTask.setTaskTitle(taskTitle);
			updateTask.setTaskText(taskText);
			updateTask.setAssignedTo(assignedTo);
			return true;
		}
		return false;
	}

	public boolean deleteTask(int taskId) {
		for (int i = 0; i < taskCount; i++) {
			if (tasks[i].getTaskId() == taskId) {
				for (int j = i; j < taskCount - 1; j++) {
					tasks[j] = tasks[j + 1];
				}
				tasks[taskCount - 1] = null;
				taskCount--;
				return true;
			}
		}
		return false;
	}

	public int getTaskCount() {
		return taskCount;
	}

	public int getMaxTasks() {
		return tasks.length;
	}
}
