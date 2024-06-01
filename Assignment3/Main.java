import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskDAO taskDAO = new TaskDAO(10); 
        
        while (choice != 0) {
            System.out.println("\nMenu:");
            System.out.println("1. Add the task.");
            System.out.println("2. Update the task.");
            System.out.println("3. Delete the task.");
            System.out.println("4. Search a task from the history.");
            System.out.println("5. Arrange the tasks.");
            System.out.println("6. Check the repeated tasks.");
            System.out.println("7. Assign a task.");
            System.out.println("0. Exit");
            System.out.print("Please enter the option number to continue: ");
            int choice = sc.nextInt();
            sc.nextLine(); 
            
            if (choice == 0) {
                System.out.println("Bye Bye.");
                break;
            }

            switch (choice) {
                case 1:
                    taskDAO.addTask();
                    break;
                case 2:
                    taskDAO.updateTask();
                    break;
                case 3:
                    taskDAO.deleteTask();
                    break;
                case 4:
                    taskDAO.searchTask();
                    break;
                case 5:
                    taskDAO.reOrderTasks();
                    break;
                case 6:
                    taskDAO.checkRepeatedTasks();
                    break;
                case 7:
                    taskDAO.assignTask();
                    break;
            }
        }

        sc.close();
    }
}
