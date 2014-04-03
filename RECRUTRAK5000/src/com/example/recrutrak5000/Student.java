package com.example.recrutrak5000;

import java.io.Serializable;

public class Student implements Serializable {
	public int id, zip, graduationYear;
	public String firstName, lastName, address, address2, city, state, email, highSchoolName, highSchoolCity;
	public boolean gender, tookTest;
	public long phone;
	public float GPA;
	public Department[] departments;
}