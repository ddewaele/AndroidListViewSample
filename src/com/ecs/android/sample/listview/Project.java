package com.ecs.android.sample.listview;

import java.util.Date;

public class Project {

	private String name;
	private Date dueDate;
	
	public Project(String name, Date dueDate) {
		super();
		this.name = name;
		this.dueDate = dueDate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
