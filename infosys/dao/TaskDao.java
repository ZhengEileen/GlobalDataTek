package com.infosys.dao;

import java.util.Random;

import com.infosys.pojo.Task;

public class TaskDao {

    private Task[] tasks;
    private boolean[] usedIds;
    private int taskCount;

    public TaskDao(int size) {
        tasks = new Task[size];
        usedIds = new boolean[1000];
        taskCount = 0;
    }

    public int generateTaskId() {
        Random randomNum = new Random();
        int taskId = 100 + randomNum.nextInt(900);
        while (usedIds[taskId]) {
            taskId = 100 + randomNum.nextInt(900);
        }
        usedIds[taskId] = true;
        return taskId;
    }

    public void addTask(String taskTitle, String taskText, String assignedTo) {
        if (taskCount >= tasks.length) {
            System.out.println("Task list is full.");
            return;
        }
        int taskId = generateTaskId();
        Task newTask = new Task(taskId, taskTitle, taskText, assignedTo, false); // Ensured proper initialization of isCompleted
        tasks[taskCount++] = newTask;
        System.out.println("Task added successfully with ID: " + taskId);
    }

    public Task[] getTasks() {
        Task[] currentTasks = new Task[taskCount];
        System.arraycopy(tasks, 0, currentTasks, 0, taskCount);
        return currentTasks;
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
                usedIds[taskId] = false;
                return true;
            }
        }
        return false;
    }
}
