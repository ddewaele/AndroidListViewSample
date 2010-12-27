package com.ecs.android.sample.listview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee {

	private String firstname;
	private String lastname;
	private String info;
	private double salary;
	private Date enrollmentDate;
	
	private List<Project> projects = new ArrayList<Project>();
	
	public Employee(String firstname, String lastname,String info,double salary,Date enrollmentDate) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.info=info;
		this.salary=salary;
		this.enrollmentDate=enrollmentDate;
	}
	
	public void addProject(Project project) {
		this.projects.add(project);
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public void removeProject(Project project) {
		this.projects.remove(project);
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
}
