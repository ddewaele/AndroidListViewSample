package com.ecs.android.sample.listview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataProvider {

	private List<Employee> employees = new ArrayList<Employee>();
	
	private DataProvider() {
		initializeData();
	}
	
	private static DataProvider instance = new DataProvider();
	
	public static DataProvider getInstance() {
		return instance;
	}
	
	private void initializeData() {
		for (int i=1 ; i<=10 ; i++) {
			Employee employee = new Employee("firstname " + i, "lastname " + i,"Some additional info on the employee " + i,i*1000,new Date());
			employees.add(employee);
			for(int j=1 ; j<=5 ; j++) {
				employee.addProject(new Project("Project " + i + "." + j,new Date(),new Date()));
			}
		}
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}
}
