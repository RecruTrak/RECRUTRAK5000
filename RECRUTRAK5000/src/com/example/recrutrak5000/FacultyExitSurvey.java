package com.example.recrutrak5000;

import android.annotation.SuppressLint;

public class FacultyExitSurvey {
	private int rating;
	//The convention for boolean answer is blank string for yes,
	//string value for the ifno fields
	private String match;
	private String directions;
	private String students;
	private String apply;
	
	@SuppressLint("NewApi")
	public FacultyExitSurvey(int r, String m, String d, String s, String a) {
		this.rating = r;
		this.match = m.isEmpty() ? "yes" : m;
		this.directions = d.isEmpty() ? "yes" : d;
		this.students = s.isEmpty() ? "yes" : s;
		this.apply = a.isEmpty() ? "yes" : a;
	}

}
