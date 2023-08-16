package com.cheffi.oauth.dto;

import io.jsonwebtoken.Claims;

public record IdTokenAttributes(Claims claims) {

	public String getEmail() {
		return (String)claims.get("email");
	}

	public String getName(String defaultName) {
		return (String)claims.getOrDefault("nickname", defaultName);
	}

	public String getProfile(String defaultProfile) {
		return (String)claims.getOrDefault("profile", defaultProfile);
	}

}
