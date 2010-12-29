package com.ecs.android.sample.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;

/**
 * Sample app with an expandable list using a custom {@link ExpandableListAdapter}
 * from {@link BaseExpandableListAdapter}.
 */
public class NestedListActivityWithListAdapter extends ExpandableListActivity {

    private ExpandableListAdapter mAdapter;

    /**
     * In the onCreate,we setup our custom ListAdapter that is responsible
     * for rendering our expandable list. 
     */
    	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SimpleExpandableListAdapter(
        			this,
        			createGroupList(),
        			R.layout.expandable_employee_row,
        			new String[] { "lastname" }, 
        			new int[] { R.id.row_lastname}, 
        			createChildList(),
        			R.layout.expandable_project_row, 
        			new String[] { "name", "startDate" }, 
        			new int[] { R.id.row_projectname, R.id.row_project_startdate} 
        			);
        setListAdapter(mAdapter);
    }

    
   	private List<Map<String,String>> createGroupList() {
   		List<Employee> employees = DataProvider.getInstance().getEmployees();
   		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
   		for (Employee employee : employees) {
   	   		Map<String,String> m = new HashMap<String,String>();
   	   	    m.put( "lastname",employee.getLastname() );
   	   		result.add( m );
		}
   		return result;
       }

     private List<List<Map<String,Object>>> createChildList() {
	   	List<List<Map<String,Object>>> result = new ArrayList<List<Map<String,Object>>>();
	   	List<Employee> employees = DataProvider.getInstance().getEmployees();
	   	for (Employee employee : employees) {
     	  List<Map<String,Object>> secList = new ArrayList<Map<String,Object>>();
     	  List<Project> projects = employee.getProjects();
     	  for (Project project : projects) {
     		    Map<String,Object> child = new HashMap<String,Object>();
         		child.put( "name", project.getName() );
         	    child.put( "startDate", Utils.getDateFormatted(project.getStartDate()) );
         		secList.add( child );	
     	  }
     	  result.add( secList );
	   	}
	   	return result;
     }

	@Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();

        String title = ((TextView) info.targetView).getText().toString();
        
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition); 
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos,
                    Toast.LENGTH_SHORT).show();
            return true;
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        return false;
    }

}
