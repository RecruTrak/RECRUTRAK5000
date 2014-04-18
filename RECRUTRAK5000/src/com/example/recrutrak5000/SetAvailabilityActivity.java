package com.example.recrutrak5000;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SetAvailabilityActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_availability_activity);
		final Faculty faculty = (Faculty) getIntent().getExtras().get("faculty");
		/*final Button setAvailability = (Button) findViewById(R.id.button1);
		setAvailability.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SetAvailabilityActivity.this, SetAvailabilityActivity.class);
				startActivity(intent);
			}
		});*/
		int availability = 0;
		CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
		if (checkBox1.isChecked()) availability += (int) Math.pow(2.0, 0.0);
		CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
		if (checkBox2.isChecked()) availability += (int) Math.pow(2.0, 1.0);
		CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
		if (checkBox3.isChecked()) availability += (int) Math.pow(2.0, 2.0);
		CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
		if (checkBox4.isChecked()) availability += (int) Math.pow(2.0, 3.0);
		CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
		if (checkBox5.isChecked()) availability += (int) Math.pow(2.0, 4.0);
		CheckBox checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
		if (checkBox6.isChecked()) availability += (int) Math.pow(2.0, 5.0);
		CheckBox checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
		if (checkBox7.isChecked()) availability += (int) Math.pow(2.0, 6.0);
		CheckBox checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
		if (checkBox8.isChecked()) availability += (int) Math.pow(2.0, 7.0);
		CheckBox checkBox9 = (CheckBox) findViewById(R.id.checkBox9);
		if (checkBox9.isChecked()) availability += (int) Math.pow(2.0, 8.0);
		CheckBox checkBox10 = (CheckBox) findViewById(R.id.checkBox10);
		if (checkBox10.isChecked()) availability += (int) Math.pow(2.0, 9.0);
		
		EditText exemptions = (EditText) findViewById(R.id.editText1);
		
		faculty.availability = availability;
		faculty.exemptions = exemptions.getText().toString();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
