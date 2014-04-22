package com.example.recrutrak5000;

import java.io.Serializable;

public class Faculty implements Serializable {
	private static final long serialVersionUID = 1L;
	public int id, availability, department;
	public long phone;
	public String firstName, lastName, email;
	public String exemptions;
	public Meeting[] meetings;
}
