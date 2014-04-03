package com.example.recrutrak5000;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RequestVisitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.request_visit_activity);
		
		
		// Set Year in school selection options..
		String years[] = {"Choose...","Senior","Junior","Sophomore","Freshman", "Transfer Student"};

		// Selection of the spinner
		Spinner spinnerYear = (Spinner) findViewById(R.id.spYearInSchool);	

		// Application of the Array to the Spinner
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinnerYear.setAdapter(spinnerArrayAdapter);
		
		// Set Country selection options..
		String[] locales = Locale.getISOCountries();
		ArrayList<String> countries = new ArrayList<String>();
		countries.add("Choose...");

		for (String countryCode : locales) {
		    Locale obj = new Locale("", countryCode);

		    System.out.println("Country Code = " + obj.getCountry() 
		        + ", Country Name = " + obj.getDisplayCountry());
		    
		    countries.add(obj.getDisplayCountry());
		}
		
		// Selection of the spinner
		Spinner spinnerCountry = (Spinner) findViewById(R.id.spCountry);
				

		// Application of the Array to the Spinner
		ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
		spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinnerCountry.setAdapter(spinnerArrayAdapter2);
		
		// Set State selection properties.. 
		String[] us_states = {"Choose...", "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
				"District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
				"Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
				"Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
				"New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
				"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
				"West Virginia", "Wisconsin", "Wyoming" };
		
		// Selection of the spinner
		Spinner spinnerState = (Spinner) findViewById(R.id.spState);
						
		// Application of the Array to the Spinner
		ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, us_states);
		spinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinnerState.setAdapter(spinnerArrayAdapter3);
		
		String[] us_states_address = {"Choose...", "Non-US", "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
				"District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
				"Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
				"Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
				"New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
				"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
				"West Virginia", "Wisconsin", "Wyoming" };
		
		// Selection of the spinner
		Spinner spinnerStateAddress = (Spinner) findViewById(R.id.spStateAddress);
						
		// Application of the Array to the Spinner
		ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, us_states_address);
		spinnerArrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinnerStateAddress.setAdapter(spinnerArrayAdapter4);
		
		// Set up submit button
		final Button submitRequest = (Button) findViewById(R.id.submitButton);
		submitRequest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(RequestVisitActivity.this).setTitle("Request submitted successfully!").setMessage("Your request was submitted successfully and will be reviewed. Check your e-mail for login instructions in order to view your scheduled meeting and/or status of your request.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						Intent intent = new Intent(RequestVisitActivity.this, MainActivity.class);
						startActivity(intent);
					}
				}).create().show();
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
