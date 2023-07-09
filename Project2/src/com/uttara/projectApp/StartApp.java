package com.uttara.projectApp;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//Importing Class
import com.uttara.projectApp.TaskBean;
import com.uttara.projectApp.TaskModel;
import com.uttara.projectApp.TaskUtil;
import com.uttara.projectApp.Constants;
import com.uttara.projectApp.Logger;
import com.uttara.projectApp.NameComparator;
import com.uttara.projectApp.DateComparator;
public class StartApp {
	public static void main(String[] args) throws IOException {
		try {
			Scanner sc1 = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			int choices = 0;
			int choice = 0;
			int sortOption = 0;
			Logger.getInstance().log("Starting Task Manager....");// logging message
			TaskModel model = new TaskModel();// creating an taskmodel class
			while (choices != 6) {
				// choice statements
				System.out.println("-------<-menu->----------");
				System.out.println("press 1 to Create Category ");
				System.out.println("press 2 to load Category ");
				System.out.println("press 3 to search Category ");
				System.out.println("press 4 to list Category ");
				System.out.println("press 5 to Delete Category");
				System.out.println("press 6 to exit ");
				System.out.println("----------------------");
				System.out.println("enter the choice");
				try {
					choices = sc1.nextInt();// reading choice from user
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid integer.");
					sc1.nextLine(); // Clearing the invalid input from the scanner
				}
				switch (choices) {
				case 1:
					System.out.println("create category");
					System.out.println("enter Category name");
					String categoryName = sc2.nextLine();
					if (TaskModel.checkCategoryExist(categoryName)) {
						System.out.println("This Category Already Exists");
					} else {
						TaskModel.createCategory(categoryName);
					}
					while (choice != 6) {
						// choice statments
						System.out.println("press 1 to Add a Task");
						System.out.println("press 2 to Edit a Task");
						System.out.println("press 3 to Remove a Task");
						System.out.println("press 4 to List a Task");
						System.out.println("press 5 to search");
						System.out.println("press 6 to Go back");
						System.out.println("----------------------");
						System.out.println("Enter the choice");
						try {
							// reading the choice
							choice = sc1.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("Invalid input. Please enter a valid integer.");
							sc1.nextLine(); // Clearing the invalid input from the scanner
						}
						switch (choice) {
						case 1:
							System.out.println("Add a task");
							// reading task details
							System.out.println("Enter the unique Task name");
							String taskName = sc2.nextLine();
							// validating task name
							while (!TaskUtil.checkValidName(taskName)
									|| TaskModel.checkTaskName(categoryName, taskName)) {
								System.out.println(
										"TaskName should not start with a digit or be blank, and it should be unique");
								System.out.println("Enter a valid name");
								taskName = sc2.nextLine();
							}
							System.out.println("Write description");
							String description = sc2.nextLine();
							System.out.println("Enter creationDate in the format dd/MM/yyyy");
							String creationDateInput = sc2.nextLine();
							// validating creation date
							while (!TaskUtil.dateValidation(creationDateInput)
									|| !TaskUtil.isDateFormatValid(creationDateInput)) {
								System.out.println("Enter a valid date in the format dd/MM/yyyy");
								creationDateInput = sc2.nextLine();
							}
							Date creationDate = new SimpleDateFormat("dd/MM/yyyy").parse(creationDateInput);
							System.out.println("Enter due date in the format dd/MM/yyyy");
							String dueDateStr = sc2.nextLine();
							// validating due date
							while (!TaskUtil.dateValidation(dueDateStr)
									|| !TaskUtil.isDueDateAfterCreationDate(dueDateStr, creationDateInput)) {
								if (!TaskUtil.dateValidation(dueDateStr)) {
									System.out.println("Enter a valid date");
								} else {
									System.out.println("Due date should be after the creation date");
								}
								dueDateStr = sc2.nextLine();
							}
							Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dueDateStr);
							System.out.println("Enter the Status");
							String status = sc2.nextLine();
							System.out.println("Enter priority for task (1 to 10):");
							int priority = 0;
							try {
								priority = sc1.nextInt();
								// validating priority
								while (!TaskUtil.priorityCheck(priority)) {
									System.out.println("Invalid priority. Enter a number between 1 and 10:");
									priority = sc1.nextInt();
								}
								// Use the 'priority' value in your code as needed
							} catch (InputMismatchException e) {
								String invalidInput = sc1.next();
								System.out
										.println("Invalid input. Priority must be a number between 1 and 10. Received: "
												+ invalidInput);
							}
							System.out.println("Enter the tags separated by commas");
							String tags = sc2.nextLine();
							// creating the task
							TaskBean bean = new TaskBean(taskName, description, creationDate, dueDate, status, priority,
									tags);
							String result = model.addTask(bean, categoryName);
							if (result.equals("SUCCESS")) {
								System.out.println("Task " + taskName + " added successfully");
							} else {
								System.out.println("Failed to add task " + taskName + ". " + result);
							}
							break;
						case 2:
							System.out.println("Edit a task");
							// reading the task
							System.out.println("Enter the task name to edit");
							String taskToEdit = sc2.nextLine();
							String existingTask = TaskModel.searchTasks(taskToEdit, categoryName);
							if (existingTask != null) {
								System.out.println("Task details");
								System.out.println(existingTask.toString());
								int editOption = 0;
								while (editOption != 7) {
									System.out.println("Press 1 to change description");
									System.out.println("Press 2 to change creation date");
									System.out.println("Press 3 to change end date");
									System.out.println("Press 4 to change status");
									System.out.println("Press 5 to change priority");
									System.out.println("Press 6 to change Tags");
									System.out.println("Press 7 to go back");
									System.out.println("----------------------");
									System.out.println("enter the choice");
									try {
										editOption = sc1.nextInt();
										sc1.nextLine();
									} catch (InputMismatchException e) {
										System.out.println("Invalid input. Please enter a valid integer.");
										sc1.nextLine();
										continue;
									}
									switch (editOption) {
									case 1:
										System.out.println("Enter the new description");
										String newDescription = sc2.nextLine();
										TaskModel.editTask(categoryName, taskToEdit, 1, newDescription);
										break;
									case 2:
										System.out.println("Enter the new creation date in the format dd/MM/yyyy");
										String newCreationDateStr = sc2.nextLine();
										while (!TaskUtil.dateValidation(newCreationDateStr)) {
											System.out.println("Enter a valid date");
											newCreationDateStr = sc2.nextLine();
										}
										Date newCreationDate = new SimpleDateFormat("dd/MM/yyyy")
												.parse(newCreationDateStr);
										SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										String newCreationDateStr1 = sdf.format(newCreationDate);
										TaskModel.editTask(categoryName, taskToEdit, 2, newCreationDateStr1);
										break;
									case 3:
										System.out.println("Enter the new due date in the format dd/MM/yyyy");
										String newDueDateStr = sc2.nextLine();
										while (!TaskUtil.dateValidation(newDueDateStr)) {
											System.out.println("Enter a valid date");
											newDueDateStr = sc2.nextLine();
										}
										Date newDueDate = new SimpleDateFormat("dd/MM/yyyy").parse(newDueDateStr);
										SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
										String newDueDateStr1 = sdf1.format(newDueDate);
										TaskModel.editTask(categoryName, taskToEdit, 3, newDueDateStr1);
										break;
									case 4:
										System.out.println("Enter the new status");
										String newStatus = sc2.nextLine();
										TaskModel.editTask(categoryName, taskToEdit, 4, newStatus);
										break;
									case 5:
										System.out.println("Enter the new priority:");
										int newPriority;
										try {
											newPriority = sc1.nextInt();
											while (!TaskUtil.priorityCheck(newPriority)) {
												System.out
														.println("Invalid priority. Enter a number between 1 and 10:");
												newPriority = sc1.nextInt();
											}
											TaskModel.editTask(categoryName, taskToEdit, 5,
													String.valueOf(newPriority));
										} catch (InputMismatchException e) {
											String invalidInput = sc1.next();
											System.out.println(
													"Invalid input. Priority must be a number between 1 and 10. Received: "
															+ invalidInput);
										}
										break;
									case 6:
										System.out.println("Enter the new tags");
										String newTags = sc2.nextLine();
										TaskModel.editTask(categoryName, taskToEdit, 6, newTags);
										break;
									case 7:
										System.out.println("Go back");
										break;
									default:
										System.out.println("Invalid option");
									}
								}
							} else {
								System.out.println("Task not found");
							}
							break;
						case 3:
							System.out.println("Remove a task");
							// deleting the task
							System.out.println("Enter the task name to delete");
							String taskToDelete = sc2.nextLine();// validating the name
							boolean deleteResult = TaskModel.deleteTask(taskToDelete, categoryName);
							if (deleteResult) {
								System.out.println("Task " + taskToDelete + " deleted successfully");
							} else {
								System.out.println("Failed to delete task " + taskToDelete + ". " + deleteResult);
							}
							break;
						case 4:
							System.out.println("List tasks");
							// listing the task
							ArrayList<String> taskList = new ArrayList<>();
							taskList = TaskModel.listofTasks(categoryName);
							if (!taskList.isEmpty()) {
								System.out.println("Task in category: " + categoryName);
								for (String task : taskList) {
									System.out.println(task);
								}
							} else {
								System.out.println("No tasks found");
							}
							while (sortOption != 5) {
								System.out.println("Press 1 to list tasks by alphabetical order of names");
								System.out.println("Press 2 to list tasks by due date");
								System.out.println("Press 3 to list tasks by creation date");
								System.out.println("Press 4 to list tasks by longest time");
								System.out.println("Press 5 to go back");
								System.out.println("------------------------");
								System.out.println("Enter the choice");
								try {
									sortOption = sc1.nextInt();
								} catch (InputMismatchException e) {
									System.out.println("Invalid input. Please enter a valid integer.");
									sc1.nextLine(); // Clear the invalid input from the scanner
								}
								ArrayList<String[]> taskLists;
								switch (sortOption) {
								case 1:
									System.out.println("Alphabetical order of the names");// listing task in
																							// alphabetical order
									taskLists = TaskModel.listTask(categoryName, 0);
									if (!taskLists.isEmpty()) {
										for (String[] array : taskLists) {
											System.out.println(String.join(" : ", array));
										}
									} else {
										System.out.println("No tasks found");
									}
									break;
								case 2:
									System.out.println("list tasks by due date");// list task by due date
									taskLists = TaskModel.listTask(categoryName, 3);
									if (!taskLists.isEmpty()) {
										for (String[] array : taskLists) {
											System.out.println(String.join(" : ", array));
										}
									} else {
										System.out.println("No tasks found");
									}
									break;
								case 3:
									System.out.println("list tasks by creaction date");// listing task by creation date
									taskLists = TaskModel.listTask(categoryName, 2);
									if (!taskLists.isEmpty()) {
										for (String[] array : taskLists) {
											System.out.println(String.join(" : ", array));
										}
									} else {
										System.out.println("No tasks found");
									}
									break;
								case 4:
									System.out.println("List tasks by long time");// listing task by duration
									taskLists = TaskModel.listTask(categoryName, 2);
									if (!taskLists.isEmpty()) {
										for (String[] array : taskLists) {
											System.out.println(String.join(" : ", array));
										}
									} else {
										System.out.println("No tasks found");
									}
									break;
								case 5:
									System.out.println("Go back");
									break;
								default:
									System.out.println("Invalid option");
									break;
								}
							}
							break;
						case 5:
							System.out.println("Search tasks");
							System.out.println("Enter the task name to search");
							String searchName = sc2.nextLine();
							// searhing task
							String searchResult = TaskModel.searchTasks(searchName, categoryName);
							if (searchResult != null) {
								System.out.println(searchResult);
							} else {
								System.out.println("Task not found");
							}
							break;
						case 6:
							System.out.println("Go back");
							break;
						default:
							System.out.println("Invalid option");
							break;
						}
					}
					break;
				case 2:
					System.out.println("Load category");
					List<String> categories = TaskModel.getAllCategories();
					// list the catergory
					if (categories.isEmpty()) {
						System.out.println("No categories found.");
					} else {
						System.out.println("Categories:");
						for (String category : categories) {
							System.out.println(category);
						}
						System.out.println("Enter the category name to load:");
						String categoryToLoad = sc2.nextLine();
						// searching catagories
						boolean found = false;
						for (String category : categories) {
							if (category.equals(categoryToLoad)) {
								found = true;
								break;
							}
						}
						if (found) {
							if (TaskModel.loadCategory(categoryToLoad)) {
								System.out.println("Category '" + categoryToLoad + "' loaded successfully");
							} else {
								System.out.println("Failed to load category '" + categoryToLoad + "'");
							}
						} else {
							System.out.println("Category '" + categoryToLoad + "' not found");
						}
					}
					break;
				case 3:
					System.out.println("Search category");
					List<String> categories1 = TaskModel.getAllCategories();
					// listing category
					if (categories1.isEmpty()) {
						System.out.println("No categories found.");
					} else {
						System.out.println("Categories:");
						for (String category : categories1) {
							System.out.println(category);
						}
						System.out.println("Enter the category name to search");
						String searchCategoryName = sc2.nextLine();
						// validating search category
						boolean searchCategoryResult = TaskModel.searchCategory(searchCategoryName);
						if (searchCategoryResult) {
							System.out.println("Category " + searchCategoryName + " found");
						} else {
							System.out.println("Category " + searchCategoryName + " not found");
						}
					}
					break;
				case 4:
					System.out.println("List category");
					// listing the category
					ArrayList<String> categoryList = TaskModel.listCategories();
					if (!categoryList.isEmpty()) {
						for (String category : categoryList) {
							System.out.println(category);
						}
					} else {
						System.out.println("No categories found");
					}
					break;
				case 5:
					System.out.println("Delete category");
					List<String> categories2 = TaskModel.getAllCategories();
					// listing the category
					if (categories2.isEmpty()) {
						System.out.println("No categories found.");
					} else {
						System.out.println("Categories:");
						for (String category : categories2) {
							System.out.println(category);
						}
						System.out.println("Enter the category name to delete:");
						String categoryToDelete = sc2.nextLine();
						// deleting the category
						boolean found = false;
						for (String category : categories2) {
							if (category.equals(categoryToDelete)) {
								found = true;
								break;
							}
						}
						if (found) {
							boolean deleteCategoryResult = TaskModel.deleteCategory(categoryToDelete);
							if (deleteCategoryResult) {
								System.out.println("Category '" + categoryToDelete + "' deleted successfully");
							} else {
								System.out.println("Failed to delete category '" + categoryToDelete + "'");
							}
						} else {
							System.out.println("Category '" + categoryToDelete + "' not found");
						}
					}
					break;
				case 6:
					System.out.println("Exit");
					break;

				default:
					System.out.println("Invalid option");
					break;
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}