package Assignment3;

import java.util.Random;

public class taskDao {
	
    private task[] tasks;
    private boolean[] usedIds;
    private int taskCount;
    
    public taskDao(int size) {
        tasks = new task[size];
        usedIds = new boolean[1000];
        taskCount = 0;
    }
    
    private int generateTaskId() {
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
        task newTask = new task();
        newTask.setTaskId(taskId);
        newTask.setTaskTitle(taskTitle);
        newTask.setTaskText(taskText);
        newTask.setAssignedTo(assignedTo);
        tasks[taskCount++] = newTask;
        System.out.println("Task added successfully with ID: " + taskId);
    }
    
    public task[] getTasks() {
        task[] currentTasks = new task[taskCount];
        System.arraycopy(tasks, 0, currentTasks, 0, taskCount);
        return currentTasks;
    }
    
    public task getTaskById(int taskId) {
        for (int i = 0; i < taskCount; i++) {
            if (tasks[i].getTaskId() == taskId) {
                return tasks[i];
            }
        }
        return null;
    }
    
    public boolean updateTask(int taskId, String taskTitle, String taskText, String assignedTo) {
        task updateTask = getTaskById(taskId);
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
                usedIds[taskId] = false;
                tasks[i] = tasks[--taskCount]; 
                tasks[taskCount] = null; 
                return true;
            }
        }
        return false;
    }
}

