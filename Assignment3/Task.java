import java.util.Random;

public class Task {
    public int TaskId;
    public String TaskTitle;
    public String TaskText;
    public String assignedTo;

    public Task() {
        this.TaskId = RandomID();
    }

    private int RandomID() {
        Random random = new Random();
        return random.nextInt(1000); 
    }
}
