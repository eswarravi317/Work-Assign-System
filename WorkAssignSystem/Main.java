package WorkAssignSystem;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static HashMap<Integer, Manager> employeeList = new HashMap<Integer, Manager>();
	
	public static void main(String[] args) {
		
		System.out.println("Work Assign System");
		boolean loop = true;
		while(loop) {
			Scanner sc = new Scanner(System.in);
			System.out.print("1.New Manager (Register)\n2.Existing Manager (Login)\n3.New Employee (Register)\n4.Existing Employee (Login)"
					+ "\nEnter choice: ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.print("Enter manager name: ");
				String mName = sc.next();
				System.out.print("Enter password: ");
				String mPassword = sc.next();
				Manager m = new Manager(mName, mPassword);
				m.registerManager(m);
				break;
			case 2:
				System.out.println("-----Authenticate Manager-----");
				System.out.print("Enter manager id: ");
				int mId = sc.nextInt();
				System.out.print("Enter password: ");
				String mPass = sc.next();
				if(authenticateManager(mId, mPass)) {
					System.out.print("5.Add Employee\n6.Assign Task\n7.Task Summary\nEnter choice: ");
					int managerChoice = sc.nextInt();
					switch(managerChoice) {
					case 5:
						Manager.addEmployee(mId);
						break;
					case 6:
						Manager.assignTask(mId);
						break;
					case 7:
						Manager.taskSummary(mId);
						break;
					default:
						System.out.println("Invlid option...Application exited");
						break;
					}
				}
				else {
					System.out.println("Authentication failed due to invalid credentials");
				}
				break;
			case 3:
				System.out.print("Enter employee name: ");
				String eName = sc.next();
				System.out.print("Enter password: ");
				String ePassword = sc.next();
				Employee e = new Employee(eName, ePassword);
				e.registerEmployee(e);
				break;
			case 4:
				System.out.println("-----Authenticate Employee-----");
				System.out.print("Enter employee id: ");
				int eId = sc.nextInt();
				System.out.print("Enter password: ");
				String ePass = sc.next();
				if(authenticateEmployee(eId, ePass)) {
					System.out.print("8.View Task\n9.Set Task Status\nEnter choice: ");
					int employeeChoice = sc.nextInt();
					switch(employeeChoice) {
					case 8:
						Employee.viewTask(eId);
						break;
					case 9:
						Employee.setStatus(eId);
						break;
					}
				}
				else {
					System.out.println("Authentication failed due to invalid credentials");
				}
				break;
			}
		}
	}
	
	public static boolean authenticateManager(int mId, String mPass) {
		if(Manager.managerList.size()!=0) {
			for(Integer m: Manager.managerList.keySet()) {
				if(mId == m && mPass.equals(Manager.managerList.get(m).managerPassword)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean authenticateEmployee(int eId, String ePass) {
		if(Employee.employeeList.size()!=0) {
			for(Integer e: Employee.employeeList.keySet()) {
				if(eId == e && ePass.equals(Employee.employeeList.get(e).employeePassword)) {
					return true;
				}
			}
		}
		return false;
	}
	
}