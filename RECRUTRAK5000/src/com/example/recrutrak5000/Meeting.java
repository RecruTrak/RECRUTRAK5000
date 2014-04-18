package com.example.recrutrak5000;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Meeting implements Serializable {
	private static final long serialVersionUID = 1L;
	public int id;
	public String date, startTime, endTime;
	public Student student;
	public Faculty faculty;
	public String location, notes;
}
