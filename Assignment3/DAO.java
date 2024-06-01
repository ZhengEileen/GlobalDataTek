import java.util.Scanner;

public class DAO {
    private Task[] tasks;
    private Scanner sc = new Scanner(System.in);

    public DAO(int size) {
        tasks = new Task[size];
    }

    public void AddTask() {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                Task task = new Task();
                System.out.println("Enter the TaskTitle:");
                task.TaskTitle = sc.nextLine();
                System.out.println("Enter the TaskText:");
                task.TaskText = sc.nextLine();
                System.out.println("Enter the assignedTo:");
                task.assignedTo = sc.nextLine();
                tasks[i] = task;
                System.out.println("Task added successfully: " + task.TaskId + ", " + task.TaskTitle + ", " + task.TaskText + ", " + task.assignedTo);
                return;
            }
        }
        System.out.println("Task list is full.");
    }

    public void UpdateTask() {
        System.out.println("Enter task ID to update:");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        Task task = FindTask(id);
        if (task != null) {
            System.out.println("Enter new TaskTitle:");
            task.TaskTitle = sc.nextLine();
            System.out.println("Enter new TaskText:");
            task.TaskText = sc.nextLine();
            System.out.println("Enter new assignedTo:");
            task.assignedTo = sc.nextLine();
            System.out.println("Task updated successfully: " + task.TaskId + ", " + task.TaskTitle + ", " + task.TaskText + ", " + task.assignedTo);
        } else {
            System.out.println("Task not found.");
        }
    }

    public void DeleteTask() {
        System.out.println("Enter task ID to delete:");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].TaskId == id) {
                tasks[i] = null;
                System.out.println("Task deleted successfully.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void SearchTask() {
        System.out.println("Enter a keyword to search for in your tasks:");
        String keyword = sc.nextLine();
        boolean found = false;
        for (Task task : tasks) {
            if (task != null && (task.TaskTitle.contains(keyword) || task.TaskText.contains(keyword) || task.assignedTo.contains(keyword))) {
                System.out.println("Task Found: " + task.TaskId + ", " + task.TaskTitle + ", " + task.TaskText + ", " + task.assignedTo);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found containing the keyword: " + keyword);
        }
    }

    public void ReOrder() {
        System.out.println("Would you like to see all the tasks in increasing or decreasing order?");
        System.out.println("Please press 1 for increasing order. Press 2 for decreasing order.");
        int order = sc.nextInt();
        sc.nextLine(); // consume newline

        Task[] tempTasks = new Task[tasks.length];
        int tempIndex = 0;
        for (Task task : tasks) {
            if (task != null) {
                tempTasks[tempIndex++] = task;
            }
        }

        for (int i = 0; i < tempIndex - 1; i++) {
            for (int j = 0; j < tempIndex - i - 1; j++) {
                if (order == 1 && tempTasks[j].TaskTitle.compareTo(tempTasks[j + 1].TaskTitle) > 0) {
                    Task temp = tempTasks[j];
                    tempTasks[j] = tempTasks[j + 1];
                    tempTasks[j + 1] = temp;
                } else if (order == 2 && tempTasks[j].TaskTitle.compareTo(tempTasks[j + 1].TaskTitle) < 0) {
                    Task temp = tempTasks[j];
                    tempTasks[j] = tempTasks[j + 1];
                    tempTasks[j + 1] = temp;
                }
            }
        }

        System.out.println("Tasks in " + (order == 1 ? "increasing" : "decreasing") + " order:");
        for (int i = 0; i < tempIndex; i++) {
            System.out.println(tempTasks[i].TaskId + ", " + tempTasks[i].TaskTitle + ", " + tempTasks[i].TaskText + ", " + tempTasks[i].assignedTo);
        }
    }

    public void checkRepeated() {
        boolean found = false;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) continue;
            for (int j = i + 1; j < tasks.length; j++) {
                if (tasks[j] != null && tasks[i].TaskTitle.equals(tasks[j].TaskTitle) && tasks[i].TaskText.equals(tasks[j].TaskText) && tasks[i].assignedTo.equals(tasks[j].assignedTo)) {
                    found = true;
                    System.out.println("Repeated task found: " + tasks[i].TaskId + ", " + tasks[i].TaskTitle + ", " + tasks[i].TaskText + ", " + tasks[i].assignedTo);
                }
            }
        }
        if (!found) {
            System.out.println("No repeated tasks found.");
        }
    }

    public void AssignTask() {
        System.out.println("Enter task ID to assign:");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        Task task = FindTask(id);
        if (task != null) {
            System.out.println("Enter the new assignee:");
            task.assignedTo = sc.nextLine();
            System.out.println("Task assigned successfully: " + task.TaskId + ", " + task.TaskTitle + ", " + task.TaskText + ", " + task.assignedTo);
        } else {
            System.out.println("Task not found.");
        }
    }

    private Task FindTask(int id) {
        for (Task task : tasks) {
            if (task != null && task.TaskId == id) {
                return task;
            }
        }
        return null;
    }
}
