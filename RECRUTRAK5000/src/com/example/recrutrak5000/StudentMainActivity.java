package com.example.recrutrak5000;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class StudentMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_main_activity);
		
		Alarm a = new Alarm(getApplicationContext());
		a.setAlarm();
		
		final Student student = (Student) getIntent().getExtras().get("student");

		

		final Button viewRequests = (Button) findViewById(R.id.button1);
		viewRequests.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudentMainActivity.this, ViewRequestsActivity.class);
				intent.putExtra("student", student);
				startActivity(intent);
			}
		});
		
		final Button viewMeetings = (Button) findViewById(R.id.button2);
		viewMeetings.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudentMainActivity.this, ViewMeetingsActivity.class);
				intent.putExtra("student", student);
				startActivity(intent);
			}
		});
		
		final Button newRequest = (Button) findViewById(R.id.button3);
		newRequest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudentMainActivity.this, RequestVisitActivity.class);
				intent.putExtra("student", student);
				startActivity(intent);
			}
		});
		
		final Button logout = (Button) findViewById(R.id.button4);
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
