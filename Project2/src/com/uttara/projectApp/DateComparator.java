package com.uttara.projectApp;
import java.util.Comparator;
public class DateComparator implements Comparator<TaskBean> {
	// compareing to taskDate
	@Override
	public int compare(TaskBean task1, TaskBean task2) {
		return task1.getDueDate().compareTo(task2.getDueDate());
	}
}
