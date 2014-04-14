package com.example.recrutrak5000;

import java.io.Serializable;


public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	public int id, guests;
	public Student student;
	public String otherAppointments, genTourInfo, visitDate, startTime, endTime;
}