package com.example.recrutrak5000;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public class RestAPI {
	private static final String url = "http://recrutrak5000-macamatic.rhcloud.com/api";
	private static final API api = new RestAdapter.Builder().setEndpoint(url).build().create(API.class);
	
	interface API {
		@GET("/studentLogin/{id}/{lastName}")
		void studentLogin(
			@Path("id") int id,
			@Path("lastName") String lastName,
			Callback<Student> cb
		);

		@GET("/staffLogin/{username}/{password}")
		void staffLogin(
			@Path("username") String username,
			@Path("password") String password,
			Callback<Staff> cb
		);

		@GET("/facultyLogin/{username}/{password}")
		void facultyLogin(
			@Path("username") String username,
			@Path("password") String password,
			Callback<Faculty> cb
		);
		
		@POST("/requests")
		void postRequest(
			@Body Request request,
			Callback<Integer> cb
		);
		
		@POST("/requests/{studentId}")
		void postRequest(
			@Body Request request,
			@Path("studentId") int studentId,
			Callback<Integer> cb
		);
	}

	public static void studentLogin(int id, String lastName, Callback<Student> cb) {
		api.studentLogin(id, lastName, cb);
	}

	public static void staffLogin(String username, String password, Callback<Staff> cb) {
		api.staffLogin(username, password, cb);
	}

	public static void facultyLogin(String username, String password, Callback<Faculty> cb) {
		api.facultyLogin(username, password, cb);
	}
	
	public static void postRequest(Request request, boolean newStudent, Callback<Integer> cb) {
		if (newStudent) {
			api.postRequest(request, cb);
		} else {
			int studentId = request.student.id;
			request.student = null;
			api.postRequest(request, studentId, cb);
		}		
	}
}
