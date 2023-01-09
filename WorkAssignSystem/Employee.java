package WorkAssignSystem;

import java.util.HashMap;
import java.util.Scanner;

public class Employee {
	static int id = 0;
	int employeeId;
	String employeeName;
	int employeeUnderManagerId;
	String employeeTask;
	String taskStatus;
	String employeePassword;
	static HashMap<Integer, Employee> employeeList = new HashMap<Integer, Employee>();
	
	Employee(String employeeName, String employeePassword){
		employeeId = ++id;
		this.employeeName = employeeName;
		this.employeePassword = employeePassword;
		employeeUnderManagerId = 0;
		employeeTask = "";
		taskStatus = "";
	}
	
	public void registerEmployee(Employee e) {
		employeeList.put(e.employeeId, e);
		System.out.println("Id: "+ e.employeeId+"\nKeep note for future use");
		System.out.println("-------------------------Employee registered successfully");
	}
	
	public static void viewTask(int eId) {
		String yourTask = employeeList.get(eId).employeeTask;
		if(yourTask == "") {
			System.out.println("Your task is - "+null);
			return;
		}
		System.out.println("Your task is - "+yourTask);
	}
	
	public static void setStatus(int eId) {
		Scanner sc = new Scanner(System.in);
		String yourTask = employeeList.get(eId).employeeTask;
		if(yourTask == "") {
			System.out.println("You doesn't have any tasks to do");
			return;
		}
		String taskStatus = employeeList.get(eId).taskStatus;
		System.out.print("\n1.In-progress\n2.On-hold\n3.Completed\nEnter choice: ");
		int taskChoice = sc.nextInt();
		switch(taskChoice) {
		case 1:
			taskStatus = "In-progress";
			employeeList.get(eId).taskStatus = taskStatus;
			Manager.managerEmployeeList.get(eId).taskStatus = taskStatus;
			System.out.println("-----------------------Task status updated successfully");
			break;
		case 2:
			taskStatus = "On-hold";
			employeeList.get(eId).taskStatus = taskStatus;
			Manager.managerEmployeeList.get(eId).taskStatus = taskStatus;
			System.out.println("-----------------------Task status updated successfully");
			break;
		case 3:
			taskStatus = "Completed";
			employeeList.get(eId).taskStatus = taskStatus;
			Manager.managerEmployeeList.get(eId).taskStatus = taskStatus;
			System.out.println("-----------------------Task status updated successfully");
			break;
		}
	}
}