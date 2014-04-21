package com.example.recrutrak5000;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ViewRequestsActivity extends Activity {
	
	private ListView lview;
	private ArrayAdapter<String> ladapter;
	private Request[] requests;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_requests_activity);
		
		final Staff staff = (Staff) getIntent().getExtras().get("staff");
		final Student student = (Student) getIntent().getExtras().get("student");
		
		lview = (ListView) findViewById(R.id.listView1);
		ArrayList<String> requestList = new ArrayList<String>();
		SimpleDateFormat intDateFormat = new SimpleDateFormat("yyyy-MM-dd"),
		                 extDateFormat = new SimpleDateFormat("MM/dd/yyyy"),
		                 intTimeFormat = new SimpleDateFormat("HH:mm:ss"),
		                 extTimeFormat = new SimpleDateFormat("h:mm a");
		String disp;
		if (staff != null) {
			requests = staff.requests;
			for (Request request : requests) {
				try {
					disp = request.student.firstName + " " + request.student.lastName + ", " +
					       extDateFormat.format(intDateFormat.parse(request.visitDate));
					requestList.add(disp);
				} catch(ParseException e) {
					e.printStackTrace();
				}
			}
		} else if (student != null) {
			requests = student.requests;
			for (Request request : requests) {
				try {
					disp = extDateFormat.format(intDateFormat.parse(request.visitDate)) + ", " +
					       extTimeFormat.format(intTimeFormat.parse(request.startTime)) + "-" +
					       extTimeFormat.format(intTimeFormat.parse(request.endTime));
					requestList.add(disp);
				} catch(ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		ladapter = new ArrayAdapter<String> (this, R.layout.row, requestList);
		lview.setAdapter(ladapter);
		
		//TODO: depending on which above is not null populate list
		if (staff != null) {
			Toast.makeText(ViewRequestsActivity.this, "Staff is logged in", Toast.LENGTH_LONG).show();
			
			lview.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					startActivity(new Intent(ViewRequestsActivity.this, StaffViewRequestActivity.class).putExtra("request", requests[position]).putExtra("staff", staff));
				}
			});
		} else if (student != null) {
			Toast.makeText(ViewRequestsActivity.this, "Student is logged in", Toast.LENGTH_LONG).show();
			
			lview.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					startActivity(new Intent(ViewRequestsActivity.this, StudentViewRequestActivity.class).putExtra("request", requests[position]).putExtra("student", student));
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
