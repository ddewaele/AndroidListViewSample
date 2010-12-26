package com.ecs.android.sample.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class SimpleListView extends ListActivity {

	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_listview);
        
        SimpleAdapter notes = new SimpleAdapter(getApplicationContext(),
	            getData(),
	            R.layout.simple_row,
	            new String[] { "firstname","lastname"},
	            new int[] { R.id.row_firstname, R.id.row_lastname} );
	
	        setListAdapter(notes);
        
    }
	
	private List<Map<String, Object>> getData() {
		//Map<String,Employee> emps = new HashMap<String, Employee>();
		List<Map<String, Object>> emps = new ArrayList<Map<String, Object>>();
        List<Employee> employees = DataProvider.getInstance().getEmployees();
        for (Employee employee : employees) {
        	
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("firstname", employee.getFirstname());
            data.put("lastname", employee.getLastname());
            emps.add(data);
			//emps.put(employee.getFirstname(), employee);
		}
        return emps;
	}
	
}