package com.example.recrutrak5000;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

public class RestAPI {
	private static final String url = "http://recrutrak5000-macamatic.rhcloud.com/api";
	private static final API api = new RestAdapter.Builder().setEndpoint(url).build().create(API.class);
	
	interface API {
		@GET("/student_login/{id}/{lastName}")
		void login(
			@Path("id") int id,
			@Path("lastName") String lastName,
			Callback<Student> cb
		);
		
		@GET("/students/{id}")
		Student getStudent(
			@Path("id") int id
		);
	}

	public static void login(int id, String lastName, Callback<Student> cb) {
		api.login(id, lastName, cb);
	}
	
	public static void getStudent(int id) {
		api.getStudent(id);
	}
}
