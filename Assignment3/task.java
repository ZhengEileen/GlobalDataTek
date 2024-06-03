package Assignment3;

public class task{
		
		private int TaskId;
		private String TaskTitle;
		private String TaskText;
		private String assignedTo;
		
		public task() {}
		
		public task(int TaskId, String TaskTitle, String TaskText, String assignedTo) {
			this.TaskId = TaskId;
			this.TaskTitle = TaskTitle;
			this.TaskText = TaskText;
			this.assignedTo = assignedTo;
		}
		
		public int GetTaskId() {
			return TaskId; 
		}
		
		public void SetTaskId(int TaskId) {
			this.TaskId = TaskId;
		}
		
		public String GetTaskTitle() {
			return TaskTitle;
		}
		
		public void SetTaskTitle(String TaskTitle) {
			this.TaskTitle = TaskTitle;
		}
		
		public String GetTaskText() {
			return TaskText;
		}
		
		public void SetTaskText(String TaskText) {
			this.TaskText = TaskText;
		} 
		
		public String GetassignedTo() {
			return assignedTo;
		}
		
		public void SetassignedTo(String assignedTo) {
			this.assignedTo = assignedTo;
		}
		
	}

