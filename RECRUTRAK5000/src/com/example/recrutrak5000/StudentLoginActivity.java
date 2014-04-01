package com.example.recrutrak5000;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentLoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_login);
				
		final Button button = (Button) findViewById(R.id.button2);
	    button.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            // Perform action on click
	        	System.out.println("login in");
	        	
	        	EditText idBox = (EditText)findViewById(R.id.fName);
	        	EditText lastNameBox = (EditText)findViewById(R.id.emailAddress);
	        	String ID = idBox.getText().toString();
	        	String lastName = lastNameBox.getText().toString();
	        	
	    		Callback<Boolean> cb = new Callback<Boolean>() {
	    		    @Override
	    		    public void success(Boolean result, Response response) {
	    		    	Intent intent = new Intent(StudentLoginActivity.this, StudentMainActivity.class);
	    				startActivity(intent);
	    		    }

	    		    @Override
	    		    public void failure(RetrofitError error) {
	    		    	new AlertDialog.Builder(StudentLoginActivity.this).setMessage("Invalid Username or Password").setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						dialog.cancel();
	    					}
	    				}).create().show();
	    		    }
	    		};
	        	RestAPI.login(ID, lastName, cb);
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
