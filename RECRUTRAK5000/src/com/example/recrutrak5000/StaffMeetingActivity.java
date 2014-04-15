package com.example.recrutrak5000;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StaffMeetingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.staff_meeting_activity);
		final Button viewLocationBtn = (Button) findViewById(R.id.viewLocationButton);
		viewLocationBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StaffMeetingActivity.this, MapViewActivity.class);
				startActivity(intent);
			}
		});
		
		final TextView studentEmailField = (TextView) findViewById(R.id.studentEmailField);
		studentEmailField.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView emailField = (TextView) findViewById(R.id.studentEmailField);
				String email = emailField.getText().toString();
				
				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
			            "mailto", email, null));
				startActivity(Intent.createChooser(intent, "Choose an Email client :"));
			}
		});
		
		final TextView studentPhoneField = (TextView) findViewById(R.id.studentPhoneField);
		studentPhoneField.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView phoneField = (TextView) findViewById(R.id.studentPhoneField);
				String phoneNumber = phoneField.getText().toString();
				
				Intent callIntent = new Intent(Intent.ACTION_CALL);
			    callIntent.setData(Uri.parse("tel:" + phoneNumber));
			    startActivity(callIntent);
			}
		});
		
		final TextView facultyEmailField = (TextView) findViewById(R.id.facultyEmailField);
		facultyEmailField.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView emailField = (TextView) findViewById(R.id.facultyEmailField);
				String email = emailField.getText().toString();
				
				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
			            "mailto", email, null));
				startActivity(Intent.createChooser(intent, "Choose an Email client :"));
			}
		});
		
		final TextView facultyPhoneField = (TextView) findViewById(R.id.facultyPhoneField);
		facultyPhoneField.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView phoneField = (TextView) findViewById(R.id.facultyPhoneField);
				String phoneNumber = phoneField.getText().toString();
				
				Intent callIntent = new Intent(Intent.ACTION_CALL);
			    callIntent.setData(Uri.parse("tel:" + phoneNumber));
			    startActivity(callIntent);
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
