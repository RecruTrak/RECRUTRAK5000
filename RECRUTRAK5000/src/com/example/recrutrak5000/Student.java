package com.example.recrutrak5000;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	public int id, zip, yearInSchool;
	public long homePhone, cellPhone;
	public boolean gender, tookTest;
	public float GPA;
	public String firstName, lastName, address, address2, city, state, country, email, highSchoolName, highSchoolCity, highSchoolState, dob;
	public List<Integer> departments;
	public Request[] requests;
}