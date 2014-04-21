package com.example.recrutrak5000;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StudentMeetingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_meeting_activity);
		
		final Meeting meeting = (Meeting) getIntent().getExtras().get("meeting");
		
		TextView date = (TextView) findViewById(R.id.meetingDateTextView);
		date.setText(meeting.date.toString());
		TextView time = (TextView) findViewById(R.id.meetingTimeTextView);
		time.setText(meeting.startTime.toString());
		TextView faculty = (TextView) findViewById(R.id.facultyTextView);
		faculty.setText(meeting.faculty.firstName + " " + meeting.faculty.lastName);
		TextView facEmail = (TextView) findViewById(R.id.emailField);
		facEmail.setText("faculty doesn't have email parameter");
		TextView facPhone = (TextView) findViewById(R.id.phoneField);
		facPhone.setText("faculty doesn't have phone parameter");
		TextView location = (TextView) findViewById(R.id.locationTextView);
		location.setText(meeting.location);
		EditText notes = (EditText) findViewById(R.id.notes);
		notes.setText(meeting.notes);
		
		final TextView viewLocationBtn = (TextView) findViewById(R.id.locationTextView);
		viewLocationBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudentMeetingActivity.this, MapViewActivity.class);
				intent.putExtra("locName", "1234 SERC");
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
