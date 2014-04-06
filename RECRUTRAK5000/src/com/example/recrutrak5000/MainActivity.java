package com.example.recrutrak5000;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button studentLogin = (Button) findViewById(R.id.button1);
		studentLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, StudentLoginActivity.class);
				startActivity(intent);
			}
		});
		final Button adminLogin = (Button) findViewById(R.id.button3);
		adminLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AdministratorLoginActivity.class);
				startActivity(intent);
			}
		});
		
		final Button requestVisit = (Button) findViewById(R.id.button2);
		requestVisit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, RequestVisitActivity.class);
				startActivity(intent);
			}
		});

		/*final Button meetingView = (Button) findViewById(R.id.button4);
		meetingView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MapViewActivity.class);
				startActivity(intent);
			}
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
