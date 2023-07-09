package com.uttara.projectApp;
import java.util.Comparator;
public class NameComparator implements Comparator<TaskBean> {
	//compareing to taskname
	@Override
	public int compare(TaskBean task1, TaskBean task2) {
		return task1.getTaskName().compareToIgnoreCase(task2.getTaskName());
	}
}
