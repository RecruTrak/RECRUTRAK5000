package com.example.recrutrak5000;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ViewFacultyActivity extends Activity {
	
	private ListView lview;
	private ArrayAdapter<String> ladapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_faculty_activity);
		
		lview = (ListView) findViewById(R.id.listView1);
		String[] meetings = new String[] {"Example 1", "Example 2"};
		ArrayList<String> meetList = new ArrayList<String>();
		meetList.addAll(Arrays.asList(meetings));
		
		ladapter = new ArrayAdapter<String> (this, R.layout.row, meetList);
		
		lview.setAdapter(ladapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
