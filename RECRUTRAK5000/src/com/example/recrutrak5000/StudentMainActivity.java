package com.example.recrutrak5000;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
public class StudentMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_main_activity);
		Student student = (Student)getIntent().getSerializableExtra("student");
		System.out.println(student.id);
		System.out.println(student.gender);
		System.out.println(student.phone);
		System.out.println(student.zip);
		System.out.println(student.firstName);
		System.out.println(student.lastName);
		System.out.println(student.address);
		System.out.println(student.city);
		System.out.println(student.state);
		System.out.println(student.email);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
