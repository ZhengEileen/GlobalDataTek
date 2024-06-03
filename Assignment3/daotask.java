package Assignment3;

import java.util.Random;

public class daotask {
    
    private task[] Tasks;
    private boolean[] UsedIds;
    private int TaskCount;
    
    public daotask(int size) {
        Tasks = new task[size];
        UsedIds = new boolean[1000];
        TaskCount = 0;
    }
    
    private int GenerateTaskId() {
        Random RandomNum = new Random();
        int TaskId = 100 + RandomNum.nextInt(900);
        while (UsedIds[TaskId]) {
            TaskId = 100 + RandomNum.nextInt(900);
        }
        UsedIds[TaskId] = true;
        return TaskId;
    }
    
    public void AddTask(String TaskTitle, String TaskText, String assignedTo) {
        if (TaskCount >= Tasks.length) {
            System.out.println("Task list is full.");
            return;
        }
        int TaskId = GenerateTaskId();
        task NewTask = new task(TaskId, TaskTitle, TaskText, assignedTo);
        Tasks[TaskCount++] = NewTask;
        System.out.println("Task added successfully with ID: " + TaskId);
    }
    
    public task[] GetTasks() {
        task[] currentTasks = new task[TaskCount];
        System.arraycopy(Tasks, 0, currentTasks, 0, TaskCount);
        return currentTasks;
    }
    
    public task GetTaskById(int TaskId) {
        for (int i = 0; i < TaskCount; i++) {
            if (Tasks[i].GetTaskId() == TaskId) {
                return Tasks[i];
            }
        }
        return null;
    }
    
    public boolean UpdateTask(int TaskId, String TaskTitle, String TaskText, String assignedTo) {
        task UpdateTask = GetTaskById(TaskId);
        if (UpdateTask != null) {
            UpdateTask.SetTaskTitle(TaskTitle);
            UpdateTask.SetTaskText(TaskText);
            UpdateTask.SetassignedTo(assignedTo);
            return true;
        }
        return false;
    }
    
    public boolean DeleteTask(int TaskId) {
        for (int i = 0; i < TaskCount; i++) {
            if (Tasks[i].GetTaskId() == TaskId) {
                UsedIds[TaskId] = false;
                Tasks[i] = Tasks[--TaskCount]; 
                Tasks[TaskCount] = null; 
                return true;
            }
        }
        return false;
    }
}
