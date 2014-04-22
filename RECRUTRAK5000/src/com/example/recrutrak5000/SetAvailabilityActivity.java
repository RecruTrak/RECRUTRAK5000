package com.example.recrutrak5000;

import java.io.IOException;
import java.io.InputStream;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SetAvailabilityActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_availability_activity);
		final Faculty faculty = (Faculty) getIntent().getExtras().get("faculty");
		final Button setAvailability = (Button) findViewById(R.id.button1);
		final CheckBox[] checkBox = {
			(CheckBox) findViewById(R.id.checkBox1),
			(CheckBox) findViewById(R.id.checkBox2),
			(CheckBox) findViewById(R.id.checkBox3),
			(CheckBox) findViewById(R.id.checkBox4),
			(CheckBox) findViewById(R.id.checkBox5),
			(CheckBox) findViewById(R.id.checkBox6),
			(CheckBox) findViewById(R.id.checkBox7),
			(CheckBox) findViewById(R.id.checkBox8),
			(CheckBox) findViewById(R.id.checkBox9),
			(CheckBox) findViewById(R.id.checkBox10)
		};
		final EditText exemptions = (EditText) findViewById(R.id.editText1);
		
		for (int i = 0; i < 10; i++) {
			if ((faculty.availability & (1 << i)) > 0) checkBox[i].toggle();
		}

		setAvailability.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int availability = 0;
				for (int i = 0; i < 10; i++) {
					if (checkBox[i].isChecked()) availability += 1 << i;
				}
				
				faculty.availability = availability;
				faculty.exemptions = exemptions.getText().toString();
				
				RestAPI.putFaculty(faculty, new Callback<Boolean>() {
					@Override
					public void success(Boolean result, Response response) {
						Toast.makeText(SetAvailabilityActivity.this, "Availability saved", Toast.LENGTH_SHORT).show();
					}
					
					@Override
					public void failure(RetrofitError error) {
						error.printStackTrace();
						if (error.getResponse() != null) {
							TypedInput err = error.getResponse().getBody();
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
					    	System.out.println(errText);
						}
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
