package com.ecs.android.sample.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SimpleCollapseExpandListview extends ListActivity 
{
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setListAdapter(new EmployeeListAdapter(this));
    }
        
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) 
    {    
       ((EmployeeListAdapter)getListAdapter()).toggle(position);
    }
    
    /**
     * A sample ListAdapter that presents content
     * from arrays of speeches and text.
     *
     */
    private class EmployeeListAdapter extends BaseAdapter {
    	
    	private String[] mEmployeeNames;
    	private String[] mEmployeeInfos;
    	private boolean[] mExpanded;
		private LayoutInflater mInflater;
    	
    	
        public EmployeeListAdapter(Context context)
        {
        	List<Employee> employees = DataProvider.getInstance().getEmployees();
        	List<String> employeeNames = new ArrayList<String>();
        	List<String> employeeInfos = new ArrayList<String>();
        	
        	for (Employee employee : employees) {
        		employeeNames.add(employee.getFirstname() + " " + employee.getLastname());
        		employeeInfos.add(employee.getInfo());
			}
        	this.mEmployeeNames = employeeNames.toArray(new String[] {});
        	this.mEmployeeInfos = employeeInfos.toArray(new String[] {});
        	this.mExpanded = new boolean[mEmployeeNames.length];
        	mInflater = LayoutInflater.from(context);
            mContext = context;
        }

        
        /**
         * The number of items in the list is determined by the number of speeches
         * in our array.
         * 
         * @see android.widget.ListAdapter#getCount()
         */
        public int getCount() {
            return mEmployeeNames.length;
        }

        /**
         * Since the data comes from an array, just returning
         * the index is sufficent to get at the data. If we
         * were using a more complex data structure, we
         * would return whatever object represents one 
         * row in the list.
         * 
         * @see android.widget.ListAdapter#getItem(int)
         */
        public Object getItem(int position) {
            return position;
        }

        /**
         * Use the array index as a unique id.
         * @see android.widget.ListAdapter#getItemId(int)
         */
        public long getItemId(int position) {
            return position;
        }

        /**
         * Make a SpeechView to hold each row.
         * @see android.widget.ListAdapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        public View getView(int position, View convertView, ViewGroup parent) {
        	
            if (null == convertView) {
            	convertView = mInflater.inflate(R.layout.row2, null);
            }
            
            TextView employeeName = (TextView) convertView.findViewById(R.id.row_employeename);
            TextView employeeInfo = (TextView) convertView.findViewById(R.id.row_employeeinfo);

            employeeName.setText(mEmployeeNames[position]);
            employeeInfo.setText(mEmployeeInfos[position]);
            employeeInfo.setVisibility(mExpanded[position] ? View.VISIBLE : View.GONE);
            
            return convertView;

        }

        public void toggle(int position) {
            mExpanded[position] = !mExpanded[position];
            notifyDataSetChanged();
        }
        
        /**
         * Remember our context so we can use it when constructing views.
         */
        private Context mContext;
        
    }
    

}
