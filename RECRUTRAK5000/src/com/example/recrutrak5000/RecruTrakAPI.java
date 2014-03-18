package com.example.recrutrak5000;

import retrofit.RestAdapter;
import retrofit.http.*;

public class RecruTrakAPI {
	private static final String url = "http://recrutrak5000-macamatic.rhcloud.com/api";
	private static final RecruTrak api = new RestAdapter.Builder().setEndpoint(url).build().create(RecruTrak.class);
	
	static class LoginResult {
		boolean success;
	}

	interface RecruTrak {
		@POST("/login/username/{username}/password/{password}")
		LoginResult login(
			@Path("username") String username,
			@Path("password") String password
		);
	}

	public boolean login(String username, String password) {
		LoginResult result = api.login(username, password);
		return result.success;
	}
}
