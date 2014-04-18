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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateMeetingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_meeting_activity);
		
		String date[] = "2014-04-18".split("-");
		
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
		
		List<String> fac = new ArrayList<String>();
		
		for(int i = 0;i < 10;i++) {
			String available = Integer.toBinaryString(256);
			if (available.charAt(offset) == '1')
				fac.add("offset" + i);
			
		}
		
		Spinner spinnerFaculty = (Spinner) findViewById(R.id.spFaculty);	
		String[] facArr = (String[]) fac.toArray();
		// Application of the Array to the Spinner
		ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, facArr);
		spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinnerFaculty.setAdapter(spinnerArrayAdapter2);
		
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
