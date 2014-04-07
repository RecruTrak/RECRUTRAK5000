package com.example.recrutrak5000;

import java.io.Serializable;

public class Faculty implements Serializable {
	private static final long serialVersionUID = 1L;
	public int id, availability;
	public Department department;
	public String exemptions;
}
