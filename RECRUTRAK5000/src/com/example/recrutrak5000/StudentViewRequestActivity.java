package com.example.recrutrak5000;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class StudentViewRequestActivity extends Activity {
	
//	String[] countryCodes = Locale.getISOCountries();
//	String[] stateCodes = {"", "AL", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN",
//		"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
//		"NC", "ND", "OH", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.student_view_request_activity);
		
		
//		// Set Year in school selection options..
//		String years[] = {"Choose...","Senior","Junior","Sophomore","Freshman", "Transfer Student"};
//
//		// Selection of the spinner
//		Spinner spinnerYear = (Spinner) findViewById(R.id.spYearInSchool);	
//
//		// Application of the Array to the Spinner
//		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
//		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//		spinnerYear.setAdapter(spinnerArrayAdapter);
//		
//		// Set Country selection options..
//		ArrayList<String> countries = new ArrayList<String>();
//		countries.add("Choose...");
//
//		for (String countryCode : countryCodes) {
//		    Locale obj = new Locale("", countryCode);
//		    countries.add(obj.getDisplayCountry());
//		}
//		
//		// Selection of the spinner
//		Spinner spinnerCountry = (Spinner) findViewById(R.id.spCountry);
//
//		// Application of the Array to the Spinner
//		ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
//		spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//		spinnerCountry.setAdapter(spinnerArrayAdapter2);
//		
//		// Set State selection properties.. 
//		String[] states = {"Choose...", "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
//				"District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
//				"Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
//				"Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
//				"New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
//				"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
//				"West Virginia", "Wisconsin", "Wyoming" };
//		
//		// Selection of the spinner
//		Spinner spinnerState = (Spinner) findViewById(R.id.spState);
//						
//		// Application of the Array to the Spinner
//		ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states);
//		spinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//		spinnerState.setAdapter(spinnerArrayAdapter3);
//		
//		// Selection of the spinner
//		Spinner spinnerStateAddress = (Spinner) findViewById(R.id.spStateAddress);
//		spinnerStateAddress.setAdapter(spinnerArrayAdapter3);
//		
//		// Set up submit button
//		final Button submitRequest = (Button) findViewById(R.id.submitButton);
//		submitRequest.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				sendRequest();
//			}
//		});
	}

	public void createMeetingClicked() {
		// handle going to create meeting page
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
