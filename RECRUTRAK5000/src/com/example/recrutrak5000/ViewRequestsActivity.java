package com.example.recrutrak5000;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
		
		//TODO: depending on which above is not null populate list
		if (staff != null) {
			Toast.makeText(ViewRequestsActivity.this, "Staff is logged in", Toast.LENGTH_LONG).show();
		} else if (student != null) {
			Toast.makeText(ViewRequestsActivity.this, "Student is logged in", Toast.LENGTH_LONG).show();
		}
		
		lview = (ListView) findViewById(R.id.listView1);
		requests = ((Student)getIntent().getSerializableExtra("student")).requests;
		ArrayList<String> requestList = new ArrayList<String>();
		SimpleDateFormat intDateFormat = new SimpleDateFormat("yyyy-MM-dd"),
		                 extDateFormat = new SimpleDateFormat("MM/dd/yyyy"),
		                 intTimeFormat = new SimpleDateFormat("HH:mm:ss"),
		                 extTimeFormat = new SimpleDateFormat("h:mm a");
		String disp;
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
		
		ladapter = new ArrayAdapter<String> (this, R.layout.row, requestList);
		lview.setAdapter(ladapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
