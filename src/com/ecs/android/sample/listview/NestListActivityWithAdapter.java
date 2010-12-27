/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ecs.android.sample.listview;

import java.util.List;

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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;

/**
 * Demonstrates expandable lists using a custom {@link ExpandableListAdapter}
 * from {@link BaseExpandableListAdapter}.
 */
public class NestListActivityWithAdapter extends ExpandableListActivity {

    ExpandableListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up our adapter
        mAdapter = new MyExpandableListAdapter(this);
        setListAdapter(mAdapter);
        //registerForContextMenu(getExpandableListView());
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

    /**
     * A simple adapter which maintains an ArrayList of photo resource Ids. 
     * Each photo is displayed as an image. This adapter supports clearing the
     * list of photos and adding a new photo.
     *
     */
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {
        
        List<Employee> employees = DataProvider.getInstance().getEmployees();
		private Context context;
        
        public MyExpandableListAdapter(Context context) {
			this.context=context;
		}
        
        public Object getChild(int groupPosition, int childPosition) {
            return employees.get(groupPosition).getProjects().get(childPosition);
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return employees.get(groupPosition).getProjects().size();
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
        	
        	Project project = (Project) getChild(groupPosition, childPosition);
        	View v = convertView;
            
        	if (v == null) {
                LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
                v = vi.inflate(R.layout.expandable_project_row, null);
            }
        	
            ImageView check = (ImageView) v.findViewById(R.id.check);
            
            if (childPosition%2==0) {
            	check.setImageResource(R.drawable.check_small);
            } else {
            	check.setImageResource(R.drawable.delete_small);
            }
        	
        	TextView projectName = (TextView) v.findViewById(R.id.row_projectname);
        	TextView projectStartDate = (TextView) v.findViewById(R.id.row_project_startdate);
        	TextView projectDueDate = (TextView) v.findViewById(R.id.row_project_duedate);
        	
            projectName.setText(project.getName());
            projectStartDate.setText(Utils.getDateFormatted(project.getStartDate()));
            projectDueDate.setText(Utils.getDateFormatted(project.getDueDate()));
            return v;
        }

        public Object getGroup(int groupPosition) {
            return employees.get(groupPosition);
        }

        public int getGroupCount() {
            return employees.size();
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            
        	Employee employee = (Employee) getGroup(groupPosition);
        	View v = convertView;
            
        	if (v == null) {
                LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
                v = vi.inflate(R.layout.expandable_employee_row, null);
            }
        	
            TextView firstName = (TextView) v.findViewById(R.id.row_firstname);
            TextView lastName = (TextView) v.findViewById(R.id.row_lastname);
            TextView info = (TextView) v.findViewById(R.id.row_info);
            TextView enrollment = (TextView) v.findViewById(R.id.row_enrollment);
            TextView salary = (TextView) v.findViewById(R.id.row_salary);
            firstName.setText(employee.getFirstname());                            
            lastName.setText(employee.getLastname());
            info.setText(employee.getInfo());
            salary.setText(String.valueOf(employee.getSalary()));
            enrollment.setText(Utils.getDateFormatted(employee.getEnrollmentDate()));
            return v;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }

    }
}
