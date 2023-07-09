package com.uttara.projectApp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
public class TaskModel {
	//adding task to a category
	public String addTask(TaskBean bean, String categoryName) {
		BufferedWriter bw = null;
		try {
			//method to add data
			bw = new BufferedWriter(new FileWriter(Constants.PATH + categoryName + ".txt", true));
			//current date
			Date D = new Date();
			Date dueDate = bean.getDueDate();
			Date creationDate = bean.getCreationDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//convert data to string
			String pldt = sdf.format(D);
			String dueDateStr = sdf.format(dueDate);
			String creationDateStr = sdf.format(creationDate);
			//add task details to file
			bw.write(bean.getTaskName() + " : " + bean.getDescription() + " : " + creationDateStr + " : " + dueDateStr
					+ " : " + bean.getStatus() + " : " + bean.getPriority() + " : " + bean.getTags() + " : " + pldt);
			bw.newLine();
			bw.newLine();
			return "SUCCESS";
		} catch (IOException e) {
			e.printStackTrace();
			return "oops something bad happend msg=" + e.getMessage();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	//readding task to a category
	public static void reAddTask(TaskBean bean, String categoryName) {
		BufferedWriter bw = null;
		try {
			//method to add data
			bw = new BufferedWriter(new FileWriter(Constants.PATH + categoryName + ".txt", true));
			Date D = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String pldt = sdf.format(D);
			//add task details to file
			bw.write(bean.getTaskName() + " : " + bean.getDescription() + " : " + bean.getCreationDate() + " : "
					+ bean.getDueDate() + " : " + bean.getTags() + " : " + bean.getPriority() + " : " + bean.getTags()
					+ " : " + pldt);
			bw.newLine();
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	//to check if a category exists
	public static boolean checkCategoryExist(String categoryName) {
		// TODO Auto-generated method stub
		return new File(Constants.PATH + categoryName + ".txt").exists();
	}
	//creating a new category
	public static void createCategory(String categoryName) {
		File f = new File(String.valueOf(Constants.PATH) + categoryName + ".txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// task validation
	public static boolean checkTaskName(String categoryName, String taskName) {
		ArrayList<TaskBean> Als = new ArrayList<>();
		File f = new File(String.valueOf(Constants.PATH) + categoryName + ".txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while ((line = br.readLine()) != null) {
				String[] sa = line.split(" : ");
				if (sa[0].contains(taskName)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
	}
	 //listing a task in a category
	public static ArrayList<String> listofTasks(String categoryName) {
		ArrayList<String> AL = new ArrayList<>();
		File f = new File(String.valueOf(Constants.PATH) + categoryName + ".txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" : ");
				AL.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return AL;
	}
	//searching the task
	public static String searchTasks(String searchName, String categoryName) {

		ArrayList<TaskBean> als = new ArrayList<>();
		File f = new File(String.valueOf(Constants.PATH) + categoryName + ".txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while ((line = br.readLine()) != null) {
				String[] sa = line.split(" : ");
				if (sa[0].contains(searchName)) {
					return line;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return "Not found";
	}
	//Method to get a TaskBean object for a task in a category
	public static TaskBean getTask(String categoryName, String taskToEdit) {
		File f = new File(String.valueOf(Constants.PATH) + categoryName + ".txt");
		BufferedReader br = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (f.exists()) {
				br = new BufferedReader(new FileReader(f));
				String line;
				while ((line = br.readLine()) != null) {
					String[] s = line.split(":");
					if (s[0].equals(taskToEdit)) {
						for (int i = 0; i < s.length; i++) {
							System.out.println(s[i]);
						}
						TaskBean t = new TaskBean();
						return t;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return null;
	}
	//editing the task
	public static String editTask(String categoryName, String taskToEdit, int editOption, String newContent) {
		File file = new File(Constants.PATH + categoryName + ".txt");
		BufferedReader reader = null;
		StringBuilder modifiedContent = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] sa = line.split(" : ");

				if (sa[0].contains(taskToEdit)) {
					sa[editOption] = newContent;
					line = String.join(" : ", sa);
				}

				modifiedContent.append(line).append("\n");
			}
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
				writer.write(modifiedContent.toString());
				System.out.println("File content modified successfully.");
			} catch (IOException e) {
				e.printStackTrace();
				return "Oops, something bad happened. Message: " + e.getMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return "Not found";
	}
	//deleting the task
	public static boolean deleteTask(String taskToDelete, String categoryName) {
		File file = new File(Constants.PATH + categoryName + ".txt");
		BufferedReader reader = null;
		StringBuilder modifiedContent = new StringBuilder();
		boolean found = false;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] sa = line.split(" : ");

				if (sa[0].contains(taskToDelete)) {
					found = true;
					continue;
				}
				modifiedContent.append(line).append("\n");
			}
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
				writer.write(modifiedContent.toString());
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return found;
	}
	//Method to sort and list tasks in a category
	public static ArrayList<String[]> listTaskWork(ArrayList<String[]> list, int sortOption) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Collections.sort(list, new Comparator<String[]>() {
			@Override
			public int compare(String[] array1, String[] array2) {
				if (sortOption >= 0 && sortOption < array1.length && sortOption < array2.length) {
					if (sortOption == 0) {
						return array1[sortOption].compareTo(array2[sortOption]);
					} else if (sortOption == 2 || sortOption == 3) {
						try {
							return sdf.parse(array1[sortOption]).compareTo(sdf.parse(array2[sortOption]));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else if (sortOption + 1 < array1.length && sortOption < array2.length) {
						try {
							long difference1 = sdf.parse(array1[sortOption + 1]).getTime()
									- sdf.parse(array1[sortOption]).getTime();
							long difference2 = sdf.parse(array2[sortOption + 1]).getTime()
									- sdf.parse(array2[sortOption]).getTime();
							return Long.compare(difference1, difference2);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
				return 0;
			}
		});
		return list;
	}
    //listing the task
	public static ArrayList<String[]> listTask(String categoryName, int sortOption) {
		ArrayList<String[]> list = new ArrayList<>();
		ArrayList<String[]> result;
		File f = new File(String.valueOf(Constants.PATH) + categoryName + ".txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while ((line = br.readLine()) != null) {
				String[] sa = line.split(" : ");
				list.add(sa);
			}
			result = listTaskWork(list, sortOption);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	// loading the category
	public static boolean loadCategory(String categoryToLoad) {
		File file = new File(Constants.PATH + categoryToLoad + ".txt");
		return file.exists();
	}
	//searching the category
	public static boolean searchCategory(String searchCategoryName) {
		File directory = new File(Constants.PATH);
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				String fileName = file.getName();
				if (fileName.startsWith(searchCategoryName) && fileName.endsWith(".txt")) {
					return true;
				}
			}
		}

		return false;
	}
	//listing the category
	public static ArrayList<String> listCategories() {
		ArrayList<String> categories = new ArrayList<>();
		File directory = new File(Constants.PATH);
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				String fileName = file.getName();
				if (fileName.endsWith(".txt")) {
					String categoryName = fileName.substring(0, fileName.lastIndexOf('.'));
					categories.add(categoryName);
				}
			}
		}
		return categories;
	}
	// deleting the category
	public static boolean deleteCategory(String categoryToDelete) {
	    File file = new File(Constants.PATH + categoryToDelete + ".txt");
	    if (file.exists()) {
	        if (file.delete()) {
	            System.out.println("Category " + categoryToDelete + " deleted the file successfully");
	            return true;
	        } else {
	            System.out.println("Failed to delete category " + categoryToDelete);
	        }
	    } else {
	        System.out.println("Category " + categoryToDelete + " not found");
	    }
	    return false;
	}	
	public static ArrayList<String> getAllCategories() {
	    ArrayList<String> categories = new ArrayList<>();
	    File directory = new File(Constants.PATH);
	    File[] files = directory.listFiles();
	    if (files != null) {
	        for (File file : files) {
	            String fileName = file.getName();
	            if (fileName.endsWith(".txt")) {
	                String categoryName = fileName.substring(0, fileName.lastIndexOf('.'));
	                categories.add(categoryName);
	            }
	        }
	    }
	    return categories;
	}
}
