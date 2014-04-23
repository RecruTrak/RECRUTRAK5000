package com.example.recrutrak5000;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ViewMeetingsActivity extends Activity {
	
	private ListView lview;
	private ArrayAdapter<String> ladapter;
	private Meeting[] meetings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_meetings_activity);
		
		final Faculty faculty = (Faculty) getIntent().getExtras().get("faculty");
		final Staff staff = (Staff) getIntent().getExtras().get("staff");
		final Student student = (Student) getIntent().getExtras().get("student");
		
		ArrayList<String> meetingList = new ArrayList<String>();
		SimpleDateFormat intDateFormat = new SimpleDateFormat("yyyy-MM-dd"),
		                 extDateFormat = new SimpleDateFormat("MM/dd/yyyy"),
		                 intTimeFormat = new SimpleDateFormat("HH:mm:ss"),
		                 extTimeFormat = new SimpleDateFormat("h:mm a");
		
		String disp;
		//TODO: depending on which above is not null populate list
		if (faculty != null) {
			Toast.makeText(ViewMeetingsActivity.this, "Faculty is logged in", Toast.LENGTH_LONG).show();
			
			meetings = faculty.meetings;
			for (Meeting m : meetings) {
				try {
					disp = m.student.firstName + " " + m.student.lastName + ", " +
					       extDateFormat.format(intDateFormat.parse(m.date));
					meetingList.add(disp);
				} catch(ParseException e) {
					e.printStackTrace();
				}
			lview = (ListView) findViewById(R.id.listView1);
			
			
			ladapter = new ArrayAdapter<String> (this, R.layout.row, meetingList);
			
			lview.setAdapter(ladapter);
			lview.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
						startActivity(new Intent(ViewMeetingsActivity.this, FacultyMeetingActivity.class).putExtra("meeting", faculty.meetings[position]));
				}
			});
			}
		} else if (staff != null) {
			Toast.makeText(ViewMeetingsActivity.this, "Staff is logged in", Toast.LENGTH_LONG).show();
			
			lview = (ListView) findViewById(R.id.listView1);
			
			meetings = staff.meetings;
			for (Meeting m : meetings) {
				try {
					disp = m.student.firstName + " " + m.student.lastName + ", " +
					       extDateFormat.format(intDateFormat.parse(m.date));
					meetingList.add(disp);
				} catch(ParseException e) {
					e.printStackTrace();
				}
			}
			
			ladapter = new ArrayAdapter<String> (this, R.layout.row, meetingList);
			
			lview.setAdapter(ladapter);
			lview.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
						startActivity(new Intent(ViewMeetingsActivity.this, StaffMeetingActivity.class).putExtra("meeting", staff.meetings[position]));
				}
			});
		} else if (student != null) {
			Toast.makeText(ViewMeetingsActivity.this, "Student is logged in", Toast.LENGTH_LONG).show();
			meetings = student.meetings;
			for (Meeting m : meetings) {
				try {
					disp = m.faculty.firstName + " " + m.faculty.lastName + " " +
							extDateFormat.format(intDateFormat.parse(m.date)) + ", " +
					       extTimeFormat.format(intTimeFormat.parse(m.startTime));
					       //extTimeFormat.format(intTimeFormat.parse(Meeting.endTime));
					meetingList.add(disp);
				} catch(ParseException e) {
					e.printStackTrace();
				}
			}
			lview = (ListView) findViewById(R.id.listView1);
			
			
			
			ladapter = new ArrayAdapter<String> (this, R.layout.row, meetingList);
			
			lview.setAdapter(ladapter);
			lview.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
						startActivity(new Intent(ViewMeetingsActivity.this, StudentMeetingActivity.class).putExtra("meeting", student.meetings[position]));
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
