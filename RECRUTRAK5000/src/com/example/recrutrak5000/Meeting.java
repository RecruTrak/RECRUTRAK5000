package com.example.recrutrak5000;

import java.sql.Date;
import java.sql.Time;

public class Meeting {
	public int id;
	public Date date;
	public Time startTime, endTime;
	public Student student;
	public Faculty faculty;
	public String location, notes;
}
