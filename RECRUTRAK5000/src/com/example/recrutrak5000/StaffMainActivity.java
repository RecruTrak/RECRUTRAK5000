package com.example.recrutrak5000;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class StaffMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.staff_main_activity);
		final Button setAvailability = (Button) findViewById(R.id.button1);
		setAvailability.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StaffMainActivity.this, ViewFacultyActivity.class);
				startActivity(intent);
			}
		});
		
		final Button viewRequests = (Button) findViewById(R.id.button2);
		viewRequests.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StaffMainActivity.this, ViewRequestsActivity.class);
				startActivity(intent);
			}
		});
		
		final Button logout = (Button) findViewById(R.id.button3);
		logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
