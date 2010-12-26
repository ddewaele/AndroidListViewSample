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
		for (int i=1 ; i<=100 ; i++) {
			Employee employee = new Employee("firstname " + i, "lastname " + i,"info " + i);
			employees.add(employee);
			for(int j=1 ; j<=20 ; j++) {
				employee.addProject(new Project("Project " + i + "." + j,new Date()));
			}
		}
	}
	public List<Employee> getEmployees() {
		return this.employees;
	}
}
