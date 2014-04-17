package com.example.recrutrak5000;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewFacultyActivity extends Activity {
	
	private ListView lview;
	private ArrayAdapter<String> ladapter;
	private Faculty[] faculty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_faculty_activity);
		
		final Staff staff = (Staff) getIntent().getExtras().get("staff");
		
		lview = (ListView) findViewById(R.id.listView1);

		ArrayList<String> facultyList = new ArrayList<String>();
		faculty = staff.department.faculty;
		for (Faculty f : faculty) {
			facultyList.add(f.lastName + ", " + f.firstName);
		}
		
		ladapter = new ArrayAdapter<String> (this, R.layout.row, facultyList);
		lview.setAdapter(ladapter);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
