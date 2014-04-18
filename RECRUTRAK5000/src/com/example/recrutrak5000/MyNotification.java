package com.example.recrutrak5000;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;




//@TargetApi(Build.VERSION_CODES.JELLY_BEAN);
/*public class MyNotification extends IntentService {

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //setContentView(R.layout.notification);
	    Intent intent = new Intent(this, MainActivity.class);
	    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

	    // Build notification
	    // Actions are just fake
	    Notification noti = new Notification.Builder(this)
	    	.setContentTitle("Submit Exit Survey")
        	.setContentText("Login and view your meeting to find the survey").setSmallIcon(R.drawable.ic_launcher)
        	.setContentIntent(pIntent).build();
	        //.addAction(R.drawable.alabamaa, "And more", pIntent).build();
	    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    // hide the notification after its selected
	    noti.flags |= Notification.FLAG_AUTO_CANCEL;

	    notificationManager.notify(0, noti);
	  }

	 @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void createNotification(View view) {
	    // Prepare intent which is triggered if the
	    // notification is selected
	    
	  }

}*/

