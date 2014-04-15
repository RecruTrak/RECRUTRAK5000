package com.example.recrutrak5000;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FacultyMeetingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faculty_meeting_activity);
		final Button viewLocationBtn = (Button) findViewById(R.id.viewLocationButton);
		viewLocationBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FacultyMeetingActivity.this, MapViewActivity.class);
				startActivity(intent);
			}
		});
		
		final Button exitSurveyBtn = (Button) findViewById(R.id.exitSurveyButton);
		exitSurveyBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FacultyMeetingActivity.this, FacultyExitSurveyActivity.class);
				startActivity(intent);
			}
		});
		
		final TextView emailField = (TextView) findViewById(R.id.emailField);
		emailField.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView emailField = (TextView) findViewById(R.id.emailField);
				String email = emailField.getText().toString();
				
				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
			            "mailto", email, null));
				startActivity(Intent.createChooser(intent, "Choose an Email client :"));
			}
		});
		
		final TextView phoneField = (TextView) findViewById(R.id.phoneField);
		phoneField.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView phoneField = (TextView) findViewById(R.id.phoneField);
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
