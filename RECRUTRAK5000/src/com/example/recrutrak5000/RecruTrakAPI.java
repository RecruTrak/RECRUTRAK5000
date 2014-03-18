package com.example.recrutrak5000;

import retrofit.http.*;

public class RecruTrakAPI {
	private static final String API_URL = "http://recrutrak5000-macamatic.rhcloud.com/api";

	static class Session {
		String message;
	}

	interface RecruTrak {
		@POST("/login/username/{username}/password/{password}")
		Session login(
			@Path("username") String username,
			@Path("password") String password
		);
	}
}
