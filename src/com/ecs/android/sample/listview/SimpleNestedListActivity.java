package com.ecs.android.sample.listview;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleNestedListActivity extends ExpandableListActivity {
    private static final String LAST_NAME = "row_lastname";
    private static final String PROJECT_NAME = "row_projectname";
    private static final String FIRST_NAME = "row_firstname";
    private static final String IS_EVEN = "IS_EVEN";
	private static final String INFO = "row_info";
	private static final String PROJECT_DUEDATE = "row_project_duedate";
	private static final String PROJECT_STARTDATE = "row_project_startdate";
    
    private ExpandableListAdapter mAdapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Map<String, String>> employees = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> employeeProjects = new ArrayList<List<Map<String, String>>>();

        List<Employee> employees2 = DataProvider.getInstance().getEmployees();

        int i=0;
        int j=0;
        for (Employee employee : employees2) {
        	
            Map<String, String> employeeMapData = new HashMap<String, String>();
            employees.add(employeeMapData);
            employeeMapData.put(LAST_NAME, employee.getLastname());
            employeeMapData.put(FIRST_NAME, employee.getFirstname());
            employeeMapData.put(INFO, employee.getInfo());
            employeeMapData.put(IS_EVEN, (i % 2 == 0) ? "This group is even" : "This group is odd");
	
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            List<Project> projects = employee.getProjects();
            j=0;
            for (Project project : projects) {
                Map<String, String> curChildMap = new HashMap<String, String>();
                children.add(curChildMap);
                curChildMap.put(PROJECT_NAME, project.getName());
                curChildMap.put(PROJECT_STARTDATE, Utils.getDateFormatted(project.getStartDate()));
                curChildMap.put(PROJECT_DUEDATE, Utils.getDateFormatted(project.getDueDate()));
                curChildMap.put(IS_EVEN, (j % 2 == 0) ? "This child is even" : "This child is odd");
                j++;
			}
            employeeProjects.add(children);
            i++;
		}
        
        
        
        // Set up our adapter
        mAdapter = new SimpleExpandableListAdapter(
                this,
                employees,
                R.layout.expandable_employee_row,
                new String[] { LAST_NAME, FIRST_NAME,INFO},
                new int[] { R.id.row_lastname, R.id.row_firstname,R.id.row_info},
                employeeProjects,
                R.layout.expandable_project_row,
                new String[] { PROJECT_NAME,PROJECT_STARTDATE,PROJECT_DUEDATE },
                new int[] { R.id.row_projectname,R.id.row_project_startdate,R.id.row_project_duedate }
                );
        setListAdapter(mAdapter);
    }

}
