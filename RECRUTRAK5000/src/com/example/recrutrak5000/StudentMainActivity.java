package com.example.recrutrak5000;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
		 // Prepare intent which is triggered if the
	    // notification is selected
	    /*Intent intent = new Intent(this, MainActivity.class);
	    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

	    // Build notification
	    // Actions are just fake
	    Notification noti = new Notification.Builder(this)
	        .setContentTitle("Submit Exit Survey")
	        .setContentText("Login and view your meeting to find the survey").setSmallIcon(R.drawable.ic_launcher)
	        .setContentIntent(pIntent).build();
	        //.addAction(R.drawable.ic_launcher, "And more", pIntent).build();
	    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    // hide the notification after its selected
	    noti.flags |= Notification.FLAG_AUTO_CANCEL;

	    notificationManager.notify(0, noti);*/
		
		/*Intent myIntent = new Intent(StudentMainActivity.this , MyNotification.class);     
	       AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	       PendingIntent pendingIntent = PendingIntent.getService(StudentMainActivity.this, 0, myIntent, 0);

	       Calendar calendar = Calendar.getInstance();
	       //calendar.set(Calendar.HOUR_OF_DAY, 19);
	       calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+1);
	       //calendar.set(Calendar.SECOND, 30);

	      alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000 , pendingIntent);  //set repeating every 24 hours
	      */
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
