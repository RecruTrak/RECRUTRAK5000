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

public class StaffMeetingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.staff_meeting_activity);
		
		final Meeting meeting = (Meeting) getIntent().getExtras().get("meeting");
		
		TextView date = (TextView) findViewById(R.id.meetingDateTextView);
		date.setText(meeting.date.toString());
		TextView time = (TextView) findViewById(R.id.meetingTimeTextView);
		time.setText(meeting.startTime.toString());
		TextView faculty = (TextView) findViewById(R.id.facultyTextView);
		faculty.setText(meeting.faculty.firstName + " " + meeting.faculty.lastName);
		final TextView facEmail = (TextView) findViewById(R.id.facultyEmailField);
		facEmail.setText("faculty doesn't have email parameter");
		final TextView facPhone = (TextView) findViewById(R.id.facultyPhoneField);
		facPhone.setText("faculty doesn't have phone parameter");
		final TextView studentEmail = (TextView) findViewById(R.id.studentEmailField);
		studentEmail.setText(meeting.student.email);
		final TextView studentPhone = (TextView) findViewById(R.id.studentPhoneField);
		studentPhone.setText("" + meeting.student.cellPhone);
		final TextView location = (TextView) findViewById(R.id.staffLocationTextView);
		location.setText(meeting.location);
		
		location.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StaffMeetingActivity.this, MapViewActivity.class);
				startActivity(intent);
			}
		});
		
		studentEmail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView emailField = (TextView) findViewById(R.id.studentEmailField);
				String email = emailField.getText().toString();
				
				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
			            "mailto", email, null));
				startActivity(Intent.createChooser(intent, "Choose an Email client :"));
			}
		});
		
		studentPhone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView phoneField = (TextView) findViewById(R.id.studentPhoneField);
				String phoneNumber = phoneField.getText().toString();
				
				Intent callIntent = new Intent(Intent.ACTION_CALL);
			    callIntent.setData(Uri.parse("tel:" + phoneNumber));
			    startActivity(callIntent);
			}
		});
		
		facEmail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView emailField = (TextView) findViewById(R.id.facultyEmailField);
				String email = emailField.getText().toString();
				
				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
			            "mailto", email, null));
				startActivity(Intent.createChooser(intent, "Choose an Email client :"));
			}
		});
		
		facPhone.setOnClickListener(new View.OnClickListener() {
			
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
