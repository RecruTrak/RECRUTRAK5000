package com.example.recrutrak5000;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
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
				sendRequest();
			}
		});
	}

	public void sendRequest() {
		EditText name = (EditText) findViewById(R.id.name);
		String rqName = name.getText().toString();
		EditText dob = (EditText) findViewById(R.id.birthDate);
		String rqDob = dob.getText().toString();
		EditText gpa = (EditText) findViewById(R.id.gpa);
		String rqGpa = gpa.getText().toString();
		EditText hsName = (EditText) findViewById(R.id.hsName);
		String rqHsName = hsName.getText().toString();
		EditText hsCity = (EditText) findViewById(R.id.hsCity);
		String rqHsCity = hsCity.getText().toString();
		EditText email = (EditText) findViewById(R.id.email);
		String rqEmail = email.getText().toString();
		EditText hPhone = (EditText) findViewById(R.id.hPhone);
		String rqHPhone = hPhone.getText().toString();
		EditText cPhone = (EditText) findViewById(R.id.cPhone);
		String rqCPhone = cPhone.getText().toString();
		EditText aLine1 = (EditText) findViewById(R.id.aLine1);
		String rqALine1 = aLine1.getText().toString();
		EditText aLine2 = (EditText) findViewById(R.id.aLine2);
		String rqALine2 = aLine2.getText().toString();
		EditText city = (EditText) findViewById(R.id.city);
		String rqCity = city.getText().toString();
		EditText zip = (EditText) findViewById(R.id.zip);
		String rqZip = zip.getText().toString();
		EditText visitDate = (EditText) findViewById(R.id.visitDate);
		String rqVisitDate = visitDate.getText().toString();
		EditText numInParty = (EditText) findViewById(R.id.numInParty);
		String rqNumInParty = numInParty.getText().toString();
		EditText startTime = (EditText) findViewById(R.id.startTime);
		String rqStartTime = startTime.getText().toString();
		EditText endTime = (EditText) findViewById(R.id.endTime);
		String rqEndTime = endTime.getText().toString();
		EditText otherAppointments = (EditText) findViewById(R.id.otherAppointments);
		String rqOtherAppointments = otherAppointments.getText().toString();
		EditText genTourInfo = (EditText) findViewById(R.id.genTourInfo);
		String rqGenTourInfo = genTourInfo.getText().toString();
		
		RadioGroup rgSatAct = (RadioGroup) findViewById(R.id.radioSATACT);
		RadioGroup rgGender = (RadioGroup) findViewById(R.id.radioGender);
		
		boolean rqGender = false;
		boolean rqTakenSatAct = false;
		
		if(rgSatAct.getCheckedRadioButtonId()!=-1){
		    int id= rgSatAct.getCheckedRadioButtonId();
		    if (id == 0) rqTakenSatAct = true;
		    else rqTakenSatAct = false;
		}
		
		if(rgGender.getCheckedRadioButtonId()!=-1){
		    int id= rgGender.getCheckedRadioButtonId();
		    if (id == 0) rqGender = true;
		    else rqGender = false;
		}
		
		List<Integer> interestedDisciplines = new ArrayList<Integer>();
		
		CheckBox cbAerospace = (CheckBox) findViewById(R.id.cbAerospace);
		if (cbAerospace.isChecked()) interestedDisciplines.add(1);
		CheckBox cbCivil = (CheckBox) findViewById(R.id.cbCivil);
		if (cbCivil.isChecked()) interestedDisciplines.add(2);
		CheckBox cbConstruction = (CheckBox) findViewById(R.id.cbConstruction);
		if (cbConstruction.isChecked()) interestedDisciplines.add(3);
		CheckBox cbComputerEng = (CheckBox) findViewById(R.id.cbComputerEng);
		if (cbComputerEng.isChecked()) interestedDisciplines.add(4);
		CheckBox cbMetallurgical = (CheckBox) findViewById(R.id.cbMetallurgical);
		if (cbMetallurgical.isChecked()) interestedDisciplines.add(5);
		CheckBox cbChemical = (CheckBox) findViewById(R.id.cbChemical);
		if (cbChemical.isChecked()) interestedDisciplines.add(6);
		CheckBox cbCompSci = (CheckBox) findViewById(R.id.cbCompSci);
		if (cbCompSci.isChecked()) interestedDisciplines.add(7);
		CheckBox cbElectrical = (CheckBox) findViewById(R.id.cbElectrical);
		if (cbElectrical.isChecked()) interestedDisciplines.add(8);
		CheckBox cbMechanical = (CheckBox) findViewById(R.id.cbMechanical);
		if (cbMechanical.isChecked()) interestedDisciplines.add(9);
		
		Request newRequest = new Request();
		Student newStudent = new Student();
		
		Spinner spYearInSchool = (Spinner)findViewById(R.id.spYearInSchool);
		String rqYearInSchool = spYearInSchool.getSelectedItem().toString();
		
		Spinner spHsState = (Spinner)findViewById(R.id.spState);
		String rqHsState = spHsState.getSelectedItem().toString();
		
		Spinner spAdState = (Spinner)findViewById(R.id.spStateAddress);
		String rqAdState = spAdState.getSelectedItem().toString();
		
		Spinner spAdCountry = (Spinner)findViewById(R.id.spCountry);
		String rqAdCountry = spAdCountry.getSelectedItem().toString();
		
		newStudent.address = rqALine1;
		newStudent.address2 = rqALine2;
		newStudent.city = rqCity;
		newStudent.email = rqEmail;
		newStudent.departments = interestedDisciplines;
		String[] names = rqName.split("\\s+");
		newStudent.firstName = names[0];
		newStudent.lastName = names[names.length - 1];
		newStudent.gender = rqGender;
		if (rqGpa != "") newStudent.GPA = Float.parseFloat(rqGpa);
		newStudent.yearInSchool = rqYearInSchool;
		newStudent.highSchoolCity = rqHsCity;
		newStudent.highSchoolName = rqHsName;
		newStudent.highSchoolState = rqHsState;
		newStudent.country = rqAdCountry;
		newStudent.state = rqAdState;
		if (rqZip != "") newStudent.zip = Integer.parseInt(rqZip);
		newStudent.homePhone = rqHPhone;
		newStudent.cellPhone = rqCPhone;
		newStudent.tookTest = rqTakenSatAct;
		newStudent.dob = rqDob;
		
		newRequest.student = newStudent;
		if (rqNumInParty != "") newRequest.guests = Integer.parseInt(rqNumInParty);
		newRequest.startTime = rqStartTime;
		newRequest.endTime = rqEndTime;
		newRequest.visitDate = rqVisitDate;
		newRequest.otherAppointments = rqOtherAppointments;
		newRequest.genTourInfo = rqGenTourInfo;
		
		System.out.println(newRequest);
		
		// Send request to DB
		
		RestAPI.postRequest(newRequest, new Callback<Boolean>() {
		    @Override
		    public void success(Boolean success, Response response) {
		    	if (success) {
			    	new AlertDialog.Builder(RequestVisitActivity.this).setTitle("Request submitted successfully!").setMessage("Your request was submitted successfully and will be reviewed. Check your e-mail for login instructions in order to view your scheduled meeting and/or status of your request.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							Intent intent = new Intent(RequestVisitActivity.this, MainActivity.class);
							startActivity(intent);
						}
					}).create().show();
		    	} else {
		    		new AlertDialog.Builder(RequestVisitActivity.this).setTitle("Submission failed!").setMessage("Please try again.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					}).create().show();
		    	}
		    }

		    @Override
		    public void failure(RetrofitError error) {
		    	new AlertDialog.Builder(RequestVisitActivity.this).setTitle("Submission failed!").setMessage("Please try again.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
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
