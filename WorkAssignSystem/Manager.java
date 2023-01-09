package WorkAssignSystem;

import java.util.HashMap;
import java.util.Scanner;

public class Manager {
	static int id = 0;
	int managerId = 0;
	String managerName = "";
	String managerPassword = "";
	static HashMap<Integer, Employee> managerEmployeeList = new HashMap<Integer, Employee>();
	
	static HashMap<Integer, Manager> managerList = new HashMap<Integer, Manager>();
	
	Manager(String managerName, String managerPassword){
		managerId = ++id;
		this.managerName = managerName;
		this.managerPassword = managerPassword;
	}

	public void registerManager(Manager m) {
		managerList.put(m.managerId, m);
		System.out.println("Id: "+ m.managerId+"\nKeep note for future use");
		System.out.println("-------------------------Manager registered successfully");
	}
	
	public static void addEmployee(int mId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------Available Free Employees-----------");
		if(Employee.employeeList.size() == 0) {
			System.out.println("No employees found");
			return;
		}
		System.out.println("EmpID      EmpName");
		System.out.println("----------------------");
		boolean found = false;
		for(Integer e: Employee.employeeList.keySet()) {
			if(Employee.employeeList.get(e).employeeUnderManagerId == 0) {
				System.out.println("    "+Employee.employeeList.get(e).employeeId+"      "+Employee.employeeList.get(e).employeeName);
				found = true;
			}
		}
		if(!found) {
			System.out.println("No employees free");
			return;
		}
		System.out.print("Enter empId to add: ");
		int empId = sc.nextInt();
		Employee e = Employee.employeeList.get(empId);
		e.employeeUnderManagerId = mId;
		managerEmployeeList.put(empId, e);
		System.out.println("--------------------Employee add successfully");
	}
	
	public static void assignTask(int mId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------Your Employees (Without Task)-----------");
		if(managerEmployeeList.size() == 0) {
			System.out.println("No employees found");
			return;
		}
		System.out.println("EmpID      EmpName");
		System.out.println("----------------------");
		boolean found = false;
		for(Integer mE: managerEmployeeList.keySet()) {
			if((managerEmployeeList.get(mE).employeeTask == "" || managerEmployeeList.get(mE).taskStatus.equals("Completed")) && (managerEmployeeList.get(mE).employeeUnderManagerId == mId)) {
				System.out.println("    "+managerEmployeeList.get(mE).employeeId+"      "+managerEmployeeList.get(mE).employeeName);
				found = true;
			}
		}
		if(!found) {
			System.out.println("No employees task-free");
			return;
		}
		System.out.print("Enter empId to assign task: ");
		int empId = sc.nextInt();
		System.out.print("Enter task: ");
		String empTask = sc.next();
		Employee e = Employee.employeeList.get(empId);
		e.employeeTask = empTask;
		e.taskStatus = "In-progress";
		managerEmployeeList.put(empId, e);
		Employee.employeeList.replace(empId, e);
		System.out.println("--------------------Task assigned successfully");
	}
	
	public static void taskSummary(int mId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------Your Employees Task Summary-----------");
		if(managerEmployeeList.size() == 0) {
			System.out.println("No employees found");
			return;
		}
		System.out.println("EmpID      EmpName      Task      Task_Status");
		System.out.println("------------------------------------------------");
		boolean found = false;
		for(Integer mE: managerEmployeeList.keySet()) {
			if(managerEmployeeList.get(mE).employeeTask != "" && (managerEmployeeList.get(mE).employeeUnderManagerId == mId)) {
				System.out.println("    "+managerEmployeeList.get(mE).employeeId+"      "+managerEmployeeList.get(mE).employeeName+"      "+managerEmployeeList.get(mE).employeeTask+"      "+managerEmployeeList.get(mE).taskStatus);
				found = true;
			}
		}
		if(!found) {
			System.out.println("No employees have tasks");
			return;
		}
	}
}