package com.uttara.projectApp;
import java.text.ParseException;
import java.util.Date;
import java.text. SimpleDateFormat;
public class TaskUtil {
	//  name is valid
	public static boolean checkValidName(String name) {

		if ((name == null) || name.trim().equals(""))
			return false;
		if (!Character.isAlphabetic(name.charAt(0)))
			return false;
		return true;
	}
	//  validating a date
	public static boolean dateValidation(String date) {
		for (int i = 0; i < date.length(); i++) {
			if (!(date.charAt(i) == '/' || Character.isDigit(date.charAt(i))))
				return false;
		}
		return true;
	}
	 // priority value is valid
	public static boolean priorityCheck(int priority) {
		return (priority <= 10) && (priority >= 1);
	}
	public static boolean isDueDateAfterCreationDate(String dueDateStr, String creationDateInput) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date dueDate = dateFormat.parse(dueDateStr);
	        Date creationDate = dateFormat.parse(creationDateInput);
	        return dueDate.after(creationDate);
	    } catch (ParseException e) {
	        return false;
	    }
	}
	public static boolean isDateFormatValid(String dateStr) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        dateFormat.setLenient(false);
	        Date parsedDate = dateFormat.parse(dateStr);
	        String formattedDate = dateFormat.format(parsedDate);
	        return formattedDate.equals(dateStr);
	    } catch (ParseException e) {
	        return false;
	    }
	}	
}
