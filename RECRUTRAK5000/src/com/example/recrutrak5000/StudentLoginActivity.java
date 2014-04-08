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
	        	int ID = Integer.parseInt(idBox.getText().toString());
	        	String lastName = lastNameBox.getText().toString();
	        	
	        	System.out.println(ID);
	        	System.out.println(lastName);
	        	RestAPI.studentLogin(ID, lastName, new Callback<Student>() {
	    		    @Override
	    		    public void success(Student student, Response response) {
	    		    	System.out.println(student.requests.length);
	    		    	Intent intent = new Intent(StudentLoginActivity.this, StudentMainActivity.class);
	    		    	intent.putExtra("student", student);
	    				startActivity(intent);
	    		    }

	    		    @Override
	    		    public void failure(RetrofitError error) {
	    		    	error.printStackTrace();
/*	    		    	TypedInput err = error.getResponse().getBody();
	    		    	char errText[] = new char[(int)err.length()];
	    		    	try {
	    		    		InputStream errStream = err.in();
	    			    	int i = 0, d;
	    			    	while ((d = errStream.read()) != -1) {
	    			    		errText[i++] = (char)d;
	    			    	}
	    		    	} catch (IOException e) {
	    		    		System.out.println("This is really bad.");
	    		    	}
	    		    	System.out.println(errText);*/
	    		    	new AlertDialog.Builder(StudentLoginActivity.this).setMessage("Invalid ID/Last Name Combination").setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						dialog.cancel();
	    					}
	    				}).create().show();
	    		    }
	    		});
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
