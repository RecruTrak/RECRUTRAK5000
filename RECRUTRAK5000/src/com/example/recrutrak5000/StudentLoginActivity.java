package com.example.recrutrak5000;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
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
	    		    	System.out.println("" + result + " - " + response);
	    		    }

	    		    @Override
	    		    public void failure(RetrofitError error) {
	    		    	error.printStackTrace();
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
