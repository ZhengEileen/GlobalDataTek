import java.util.Arrays;
import java.util.Scanner;

public class TodoManager{
    
    public String GetTask(){
        Scanner UserInput = new Scanner(System.in);
        System.out.println("Please type your 5 tasks for today, each task should end with a period:");
        String tasks = UserInput.nextLine();
        return tasks;
        
    }
    
    public void ReOrder(String tasks) {
        Scanner UserInput = new Scanner(System.in);
        System.out.println("Would you like to see all the tasks in increasing or decreasing order?");
        System.out.println("Please press 1 for increasing order.Press 2 for decreasing order.");
        String[] temp = tasks.split("\\.");
        int order = UserInput.nextInt();
        
        if (order == 1) {
            Arrays.sort(temp);
            System.out.println("Tasks in increasing order:");
            for(int i =0; i<temp.length;i++){
                System.out.println(temp[i]);
            }
        } 
        else{
            Arrays.sort(temp);
            System.out.println("Tasks in decreasing order:");
            for(int i=temp.length-1; i>=0;i--){
                System.out.println(temp[i]);
            }
        }
     
    }
    
    public void checkRepeated(String tasks) {
        String[] temp = tasks.split("\\.");
        String repeat = "";
        
        for (int i = 0; i < temp.length; i++) {
            for (int j = i + 1; j < temp.length; j++) {
                if (temp[i].equals(temp[j])) {
                    repeat = repeat + temp[i] + ".";
                }
            }
        }
        
        if (repeat != "") {
            System.out.println("Repeated task found: " + repeat);
        } 
        else {
            System.out.println("No repeated tasks found.");
        }
    }
    
	public static void main(String[] args){
	    Scanner UserInput = new Scanner(System.in);
        System.out.println("Hi, Please enter your name:");
        String UserName = UserInput.nextLine();
        System.out.println("Hi "+ UserName);
        
        TodoManager todoManager = new TodoManager();
        String tasks = todoManager.GetTask();
        
        todoManager.ReOrder(tasks);
        
        todoManager.checkRepeated(tasks);
        
    }

}