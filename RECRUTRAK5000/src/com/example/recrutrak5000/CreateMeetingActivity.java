package com.example.recrutrak5000;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateMeetingActivity extends Activity {
	Request request;
	Student student;
	Staff staff;
	Faculty faculty;
	String startTime, endTime, location;

	Meeting newMeeting = new Meeting();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_meeting_activity);
		request = (Request) getIntent().getExtras().get("request");
		student = (Student) getIntent().getExtras().get("student");
		staff = (Staff) getIntent().getExtras().get("staff");

		
		String date[] = request.visitDate.split("-");
		
		final TextView viewLocationBtn = (TextView) findViewById(R.id.createMeetingButton);
		
		String times[] = {"Choose...", "8:30", "3:30"};
		String locations[] = {"Choose...", "1130 SEC", "2200 SERC", "114 HM Comer"};
		//TODO verify locations
		// Selection of the spinner
		Spinner spinnerTime = (Spinner) findViewById(R.id.spMeetingTime);	

		// Application of the Array to the Spinner
		ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);
		spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinnerTime.setAdapter(spinnerArrayAdapter1);
		int timeIdx = spinnerTime.getSelectedItemPosition();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
		int dayOffset = cal.get(Calendar.DAY_OF_WEEK)-1;
		int offset = (dayOffset*2) + (timeIdx-1);
		
		final List<Faculty> fac = new ArrayList<Faculty>();
		
		for(Faculty f:staff.department.faculty) {
			String available = Integer.toBinaryString(f.availability);
			while(available.length() < 10) {
				available = "0"+available;
			}
			if (available.length() >= offset && available.charAt(offset) == '1')
				fac.add(f);
		}
		
		
		
		final Spinner spinnerFaculty = (Spinner) findViewById(R.id.spFaculty);	
		final String[] facDisp = new String[fac.size()];// = (String[]) fac.toArray();
		for(int i =0;i < fac.size();i++) {
			facDisp[i] = fac.get(i).firstName + " " + fac.get(i).lastName;
		}
		// Application of the Array to the Spinner
		ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, facDisp);
		spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinnerFaculty.setAdapter(spinnerArrayAdapter2);
		
		AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView exemptions = (TextView) findViewById(R.id.textView6);
				String fname = facDisp[spinnerFaculty.getSelectedItemPosition()].split(" ")[0];
				String lname = facDisp[spinnerFaculty.getSelectedItemPosition()].split(" ")[1];
				for(Faculty f: fac) {
					if(f.firstName.equals(fname) && f.lastName.equals(lname)) {
						faculty = f;
						break;
					}
						
					
				}
				//Faculty f = (Faculty) spinnerFaculty.getSelectedItem();
				//exemptions.setText(faculty.exemptions);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		spinnerFaculty.setOnItemSelectedListener(listener);
		
		
		
		
		
		viewLocationBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				createMeeting();
			}
		});
		
		// TODO: populate spinners
	}
	
	public void createMeeting() {
			
		
		newMeeting.date = request.visitDate;
		newMeeting.endTime = "";
		newMeeting.faculty = faculty;
		//newMeeting.id = ;	set by server
		newMeeting.location = location;
		newMeeting.startTime = startTime;
		newMeeting.student = student;
		
		
		
		
		System.out.println(newMeeting);
		
		// Send request to DB
		
		RestAPI.postMeeting(newMeeting, request.id, student.id, faculty.id, staff.id,  new Callback<Integer>() {
//public static void postMeeting(Meeting meeting, int requestId, int studentId, int facultyId, int staffId, Callback<Integer> cb) {
		    @Override
		    public void success(Integer meetingId, Response response) {
		    	if (meetingId > 0) {
		    		newMeeting.id = meetingId;
		    		Toast.makeText(CreateMeetingActivity.this, "Meeting ID: " + meetingId, Toast.LENGTH_LONG).show();
			    	new AlertDialog.Builder(CreateMeetingActivity.this).setTitle("Meeting submitted successfully!").setMessage("Meeting Created. Emails will be sent to all participants with the relevant information.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							Intent intent = new Intent(CreateMeetingActivity.this, MainActivity.class);
							startActivity(intent);
						}
					}).create().show();
		    	} else {
		    		new AlertDialog.Builder(CreateMeetingActivity.this).setTitle("Submission failed!").setMessage("Please try again.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					}).create().show();
		    	}
		    }

		    @Override
		    public void failure(RetrofitError error) {
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
		    	new AlertDialog.Builder(CreateMeetingActivity.this).setTitle("Submission failed!").setMessage("Please try again.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
