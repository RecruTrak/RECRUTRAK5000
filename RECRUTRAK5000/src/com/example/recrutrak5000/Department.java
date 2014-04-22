package com.example.recrutrak5000;

import java.io.Serializable;

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	public int id;
	public String name;
	public Faculty[] faculty;
}
