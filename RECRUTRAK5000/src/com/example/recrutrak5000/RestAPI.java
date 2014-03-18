package com.example.recrutrak5000;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

public class RestAPI {
	private static final String url = "http://recrutrak5000-macamatic.rhcloud.com/api";
	private static final API api = new RestAdapter.Builder().setEndpoint(url).build().create(API.class);
	
	interface API {
		@GET("/login/username/{username}/password/{password}")
		void login(
			@Path("username") String username,
			@Path("password") String password,
			Callback<Boolean> cb
		);
	}

	public static void login(String username, String password, Callback<Boolean> cb) {
		api.login(username, password, cb);
	}
}
