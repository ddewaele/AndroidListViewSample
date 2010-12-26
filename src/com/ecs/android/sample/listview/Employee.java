package com.ecs.android.sample.listview;

import java.util.ArrayList;
import java.util.List;

public class Employee {

	private String firstname;
	private String lastname;
	private String info;
	private List<Project> projects = new ArrayList<Project>();
	
	public Employee(String firstname, String lastname,String info) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.info=info;
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
	
}
