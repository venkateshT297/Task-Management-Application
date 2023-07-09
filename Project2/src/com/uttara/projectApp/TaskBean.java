package com.uttara.projectApp;
import java.util.Date;
import java.util.Objects;
public class TaskBean {
	 // Task properties
	private String taskName;
	private String description;
	private String status;
	private Date creationDate;
	private Date dueDate;
	private String tags;
	private int priority;
	public TaskBean() {
		// empty constructor
	}
	public TaskBean(String taskName, String description, Date creationDate, Date dueDate, String status, int priority,
			String tags) {
		super();
		this.taskName = taskName;
		this.description = description;
		this.status = status;
		this.creationDate = creationDate;
		this.dueDate = dueDate;
		this.tags = tags;
		this.priority = priority;
	}
	 // Getters and setters 
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public int hashCode() {
		// Generateing hashcode
		return Objects.hash(creationDate, description, dueDate, priority, status, tags, taskName);
	}
	@Override
	public boolean equals(Object obj) {
		// Checking its equal
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(description, other.description)
				&& Objects.equals(dueDate, other.dueDate) && priority == other.priority
				&& Objects.equals(status, other.status) && Objects.equals(tags, other.tags)
				&& Objects.equals(taskName, other.taskName);
	}
	@Override
	public String toString() {
		 // Returns into  tostring
		return "TaskBean [taskName=" + taskName + ", description=" + description + ", status=" + status
				+ ", creationDate=" + creationDate + ", dueDate=" + dueDate + ", tags=" + tags + ", priority="
				+ priority + "]";
	}
}