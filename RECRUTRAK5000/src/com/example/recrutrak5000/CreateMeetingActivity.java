package com.example.recrutrak5000;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateMeetingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_meeting_activity);
		final Request request = (Request) getIntent().getExtras().get("request");
		final Student student = (Student) getIntent().getExtras().get("student");
		final Staff staff = (Staff) getIntent().getExtras().get("staff");

		
		String date[] = request.visitDate.split("-");
		
		final TextView viewLocationBtn = (TextView) findViewById(R.id.createMeetingButton);
		
		String times[] = {"Choose...", "8:30", "3:30"};
		
		// Selection of the spinner
		Spinner spinnerTime = (Spinner) findViewById(R.id.spMeetingTime);	

		// Application of the Array to the Spinner
		ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);
		spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinnerTime.setAdapter(spinnerArrayAdapter1);
		int timeIdx = spinnerTime.getSelectedItemPosition();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
		int dayOffset = cal.get(Calendar.DAY_OF_WEEK)-1;
		int offset = (dayOffset*2) + (timeIdx-1);
		
		List<Faculty> fac = new ArrayList<Faculty>();
		
		for(Faculty f:staff.department.faculty) {
			String available = Integer.toBinaryString(f.availability);
			if (available.charAt(offset) == '1')
				fac.add(f);
		}
		
		
		
		final Spinner spinnerFaculty = (Spinner) findViewById(R.id.spFaculty);	
		String[] facDisp = {};// = (String[]) fac.toArray();
		for(int i =0;i < fac.size();i++) {
			facDisp[i] = fac.get(i).firstName + " " + fac.get(i).lastName;
		}
		// Application of the Array to the Spinner
		ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, facDisp);
		spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinnerFaculty.setAdapter(spinnerArrayAdapter2);
		
		AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView exemptions = (TextView) findViewById(R.id.textView6);
				Faculty f = (Faculty) spinnerFaculty.getSelectedItem();
				exemptions.setText(f.exemptions);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		spinnerFaculty.setOnItemSelectedListener(listener);
		
		
		
		
		
		viewLocationBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(CreateMeetingActivity.this, MapViewActivity.class);
//				startActivity(intent);
			}
		});
		
		// TODO: populate spinners
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
