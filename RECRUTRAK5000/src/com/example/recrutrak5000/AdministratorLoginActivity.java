package com.example.recrutrak5000;

import java.io.IOException;
import java.io.InputStream;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AdministratorLoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.administrator_login_activity);
		final Button login = (Button) findViewById(R.id.button2);
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				EditText usernameBox = (EditText)findViewById(R.id.fName);
	        	EditText passwordBox = (EditText)findViewById(R.id.password);
	        	String username = usernameBox.getText().toString();
	        	String password = passwordBox.getText().toString();
	        	RadioGroup typeGroup = (RadioGroup)findViewById(R.id.radioStatus);
	        	int type = typeGroup.indexOfChild(typeGroup.findViewById(typeGroup.getCheckedRadioButtonId()));
	        	
	        	System.out.println(type);
	        	
	        	switch(type) {
	        		case 0:
	        			RestAPI.staffLogin(username, password, new Callback<Staff>() {
	        				@Override
	        				public void success(Staff staff, Response response) {
	        					Intent intent = new Intent(AdministratorLoginActivity.this, StaffMainActivity.class);
	    	    		    	intent.putExtra("staff", staff);
	    	    				startActivity(intent);
	        				}
	        				
	        				@Override
	        				public void failure(RetrofitError error) {
	    	    		    	error.printStackTrace();
	        					new AlertDialog.Builder(AdministratorLoginActivity.this).setMessage("Invalid Username/Password Combination").setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    	    					public void onClick(DialogInterface dialog,int id) {
	    	    						dialog.cancel();
	    	    					}
	    	    				}).create().show();
	        				}
	        			});
	        			break;
	        		case 1:
	        			RestAPI.facultyLogin(username, password, new Callback<Faculty>() {
	        				@Override
	        				public void success(Faculty faculty, Response response) {
	        					Intent intent = new Intent(AdministratorLoginActivity.this, FacultyMainActivity.class);
	    	    		    	intent.putExtra("faculty", faculty);
	    	    				startActivity(intent);
	        				}
	        				
	        				@Override
	        				public void failure(RetrofitError error) {
	        					new AlertDialog.Builder(AdministratorLoginActivity.this).setMessage("Invalid Username/Password Combination").setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    	    					public void onClick(DialogInterface dialog,int id) {
	    	    						dialog.cancel();
	    	    					}
	    	    				}).create().show();
	        				}
	        			});
	        	}
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
