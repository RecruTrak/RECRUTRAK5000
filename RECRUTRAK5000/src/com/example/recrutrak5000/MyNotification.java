package com.example.recrutrak5000;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;





@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MyNotification extends BroadcastReceiver {
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context arg0, Intent arg1) {
			    
	    Notification noti = new Notification.Builder(arg0)
	    	.setSmallIcon(R.drawable.ic_launcher)
	    	.setContentTitle("Submit Exit Survey")
        	.setContentText("Login and view your meeting to find the survey").setSmallIcon(R.drawable.ic_launcher).build();
        	//.setContentIntent(pIntent).build();
	    Intent intent = new Intent(arg0, MainActivity.class);
	    TaskStackBuilder sBuild = TaskStackBuilder.create(arg0);
	    sBuild.addParentStack(MainActivity.class);
	    sBuild.addNextIntent(intent);
	    PendingIntent pIntent = sBuild.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
	    noti.setLatestEventInfo(arg0, "Meeting Reminder", "You have a meeting scheduled in two hours. Login to view details.", pIntent);
	    NotificationManager notificationManager = (NotificationManager) arg0.getSystemService(Context.NOTIFICATION_SERVICE);
	    // hide the notification after its selected
	    noti.flags |= Notification.FLAG_AUTO_CANCEL;

	    notificationManager.notify(R.string.action_settings, noti);
	  }

	 


	

}

