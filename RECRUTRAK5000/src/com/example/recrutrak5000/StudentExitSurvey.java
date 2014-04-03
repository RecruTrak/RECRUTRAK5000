package com.example.recrutrak5000;

import android.annotation.SuppressLint;

public class StudentExitSurvey {
	private int rating;
	//The convention for boolean answer is blank string for yes,
	//string value for the ifno fields
	private String questions;
	private String directions;
	private String students;
	private String apply;
	
	@SuppressLint("NewApi")
	public StudentExitSurvey(int r, String q, String d, String s, String a) {
		this.rating = r;
		this.questions = q.isEmpty() ? "yes" : q;
		this.directions = d.isEmpty() ? "yes" : d;
		this.students = s.isEmpty() ? "yes" : s;
		this.apply = a.isEmpty() ? "yes" : a;
	}

}
