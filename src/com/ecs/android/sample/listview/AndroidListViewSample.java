package com.ecs.android.sample.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AndroidListViewSample extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
//    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		Button btnSimpleListView = (Button) findViewById(R.id.btn_simple_listview1);
		btnSimpleListView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent().setClass(v.getContext(),SimpleListView.class));
			}
		});      
		
		Button btnCollapseListView = (Button) findViewById(R.id.btn_collapsable_listview1);
		btnCollapseListView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent().setClass(v.getContext(),SimpleCollapseExpandListview.class));
			}
		});  
		
		Button btnNestedListView = (Button) findViewById(R.id.btn_nested_listview1);
		btnNestedListView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent().setClass(v.getContext(),SimpleNestedListActivity.class));
			}
		});  	
		
		Button btnNestedListViewAdapter = (Button) findViewById(R.id.btn_nested_listview_adapter);
		btnNestedListViewAdapter.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent().setClass(v.getContext(),NestedListActivityWithCustomAdapter.class));
			}
		});  	
		
		Button btnNestedListViewListAdapter = (Button) findViewById(R.id.btn_nested_listview_list_adapter);
		btnNestedListViewListAdapter.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent().setClass(v.getContext(),NestedListActivityWithListAdapter.class));
			}
		});
		
    }
}