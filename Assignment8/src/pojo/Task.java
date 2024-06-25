package pojo;

public class Task {

	private int taskId;
	private String TaskTitle;
	private String TaskText;
	private String assignedTo;
	private boolean isComplete;

	public Task(int taskId, String TaskTitle, String TaskText, String assignedTo, boolean isComplete) {
		this.taskId = taskId;
		this.TaskTitle = TaskTitle;
		this.TaskText = TaskText;
		this.assignedTo = assignedTo;
		this.isComplete = isComplete;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return TaskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		TaskTitle = taskTitle;
	}

	public String getTaskText() {
		return TaskText;
	}

	public void setTaskText(String taskText) {
		TaskText = taskText;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
