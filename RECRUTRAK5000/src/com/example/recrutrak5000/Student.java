package com.example.recrutrak5000;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
	public int id, zip;
	public String firstName, lastName, address, address2, city, homePhone, cellPhone, dob, state, country, email, highSchoolName, highSchoolCity, highSchoolState, yearInSchool;
	public boolean gender, tookTest;
	public float GPA;
	public List<Integer> departments;
}