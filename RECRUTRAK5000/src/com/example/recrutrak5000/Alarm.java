package com.example.recrutrak5000;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Alarm {
	private PendingIntent pIntent;
	private Intent myIntent;
	private Context context;
	public Alarm(Context context) {
		
		this.context = context;
		myIntent = new Intent(context , MyNotification.class);
		pIntent = PendingIntent.getService(context, 0, myIntent, 0);
	}
	
	public void setAlarm() {
		Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.SECOND, 4);
	    //calendar.set(Calendar.HOUR_OF_DAY, 19);
	    //calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
	    //calendar.set(Calendar.SECOND, 30);
	   AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	   alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
	}
	
    
    
    
    
}
