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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StaffViewRequestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.staff_view_request_activity);
		final Request request = (Request) getIntent().getExtras().get("request");

		final Student student = request.student;
		final Staff staff = (Staff) getIntent().getExtras().get("staff");
		
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(student.firstName + " " + student.lastName);
		
		TextView dob = (TextView) findViewById(R.id.birthDate);
		dob.setText(student.dob);
		
		TextView spYearInSchool = (TextView) findViewById(R.id.spYearInSchool);
		if (student.yearInSchool == 1)
			spYearInSchool.setText("Senior");
		else if (student.yearInSchool == 2)
			spYearInSchool.setText("Junior");
		else if (student.yearInSchool == 3)
			spYearInSchool.setText("Sophomore");
		else if (student.yearInSchool == 4)
			spYearInSchool.setText("Freshman");
		else if (student.yearInSchool == 5)
			spYearInSchool.setText("Transfer Student");
		else
			spYearInSchool.setText("N/A");
	
		TextView gpa = (TextView) findViewById(R.id.gpa);
		gpa.setText("" + student.GPA);
		
		TextView hsName = (TextView) findViewById(R.id.hsName);
		hsName.setText(student.highSchoolName);
		
		TextView hsCity = (TextView) findViewById(R.id.hsCity);
		hsCity.setText(student.highSchoolCity);
		
		TextView spState = (TextView) findViewById(R.id.spState);
		spState.setText(student.highSchoolState);
		
		TextView email = (TextView) findViewById(R.id.email);
		email.setText(student.email);
		
		TextView hPhone = (TextView) findViewById(R.id.hPhone);
		hPhone.setText("" + student.homePhone);
		
		TextView cPhone = (TextView) findViewById(R.id.cPhone);
		cPhone.setText("" + student.cellPhone);
		
		TextView aLine1 = (TextView) findViewById(R.id.aLine1);
		aLine1.setText(student.address);
		
		TextView aLine2 = (TextView) findViewById(R.id.aLine2);
		aLine2.setText(student.address2);
		
		TextView city = (TextView) findViewById(R.id.city);
		city.setText(student.city);
		
		TextView spStateAddress = (TextView) findViewById(R.id.spStateAddress);
		spStateAddress.setText(student.state);
		
		TextView zip = (TextView) findViewById(R.id.addressZip);
		//zip.setText("" + student.zip);
		zip.setText("testZip");
		
		TextView spCountry = (TextView) findViewById(R.id.spCountry);
		spCountry.setText(student.country);
		
		TextView visitDate = (TextView) findViewById(R.id.visitDate);
		visitDate.setText(request.visitDate);
		
		TextView numInParty = (TextView) findViewById(R.id.numInParty);
		numInParty.setText("" + request.guests);
		
		TextView startTime = (TextView) findViewById(R.id.startTime);
		startTime.setText(request.startTime);
		
		TextView endTime = (TextView) findViewById(R.id.endTime);
		endTime.setText(request.endTime);
		
		TextView otherAppointments = (TextView) findViewById(R.id.otherAppointments);
		otherAppointments.setText(request.otherAppointments);
		
		TextView genTourInfo = (TextView) findViewById(R.id.genTourInfo);
		genTourInfo.setText(request.genTourInfo);
		
		RadioGroup gender = (RadioGroup) findViewById(R.id.radioGender);
		RadioButton female = (RadioButton) gender.getChildAt(1);
		RadioButton male = (RadioButton) gender.getChildAt(0);
		male.setClickable(false);
		female.setClickable(false);
		
		if (student.gender) {
			male.toggle();
		} else {
			female.toggle();
		}
		
		RadioGroup satAct = (RadioGroup) findViewById(R.id.radioSATACT);
		RadioButton yes = (RadioButton) satAct.getChildAt(0);
		RadioButton no = (RadioButton) satAct.getChildAt(1);
		yes.setClickable(false);
		no.setClickable(false);
		
		if (student.tookTest) {	
			yes.toggle();
		} else {
			no.toggle();
		}
		
		CheckBox aero = (CheckBox) findViewById(R.id.cbAerospace);
		aero.setClickable(false);
		CheckBox civil = (CheckBox) findViewById(R.id.cbCivil);
		civil.setClickable(false);
		CheckBox constr = (CheckBox) findViewById(R.id.cbConstruction);
		constr.setClickable(false);
		CheckBox comp = (CheckBox) findViewById(R.id.cbComputerEng);
		comp.setClickable(false);
		CheckBox metal = (CheckBox) findViewById(R.id.cbMetallurgical);
		metal.setClickable(false);
		CheckBox chem = (CheckBox) findViewById(R.id.cbChemical);
		chem.setClickable(false);
		CheckBox compsci = (CheckBox) findViewById(R.id.cbCompSci);
		compsci.setClickable(false);
		CheckBox electr = (CheckBox) findViewById(R.id.cbElectrical);
		electr.setClickable(false);
		CheckBox mech = (CheckBox) findViewById(R.id.cbMechanical);
		mech.setClickable(false);
		CheckBox notsure = (CheckBox) findViewById(R.id.cbNotSure);
		notsure.setClickable(false);
		
//		if (student.departments != null) {
//			if (student.departments.contains(0)) {
//				aero.toggle();
//			} else if (student.departments.contains(1)) {
//				civil.toggle();
//			} else if (student.departments.contains(2)) {
//				constr.toggle();
//			} else if (student.departments.contains(3)) {
//				comp.toggle();
//			} else if (student.departments.contains(4)) {
//				metal.toggle();
//			} else if (student.departments.contains(5)) {
//				chem.toggle();
//			} else if (student.departments.contains(6)) {
//				compsci.toggle();
//			} else if (student.departments.contains(7)) {
//				electr.toggle();
//			} else if (student.departments.contains(8)) {
//				mech.toggle();
//			} else if (student.departments.contains(9)) {
//				notsure.toggle();
//			}
//		}
		if (student.departments != null) {
			if (student.departments.size() == 0) {
				notsure.toggle();
			} else {
				if (student.departments.contains(1)) aero.toggle();
				if (student.departments.contains(2)) civil.toggle();
				if (student.departments.contains(3)) constr.toggle();
				if (student.departments.contains(4)) comp.toggle();
				if (student.departments.contains(5)) metal.toggle();
				if (student.departments.contains(6)) chem.toggle();
				if (student.departments.contains(7)) compsci.toggle();
				if (student.departments.contains(8)) electr.toggle();
				if (student.departments.contains(9)) mech.toggle();
			}
		}
		
		

//		// Set up create meeting button
		final Button createMeeting = (Button) findViewById(R.id.submitButton);
		createMeeting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(StaffViewRequestActivity.this, CreateMeetingActivity.class).putExtra("request", request).putExtra("student", student).putExtra("staff", staff));
				
			}
		});
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
