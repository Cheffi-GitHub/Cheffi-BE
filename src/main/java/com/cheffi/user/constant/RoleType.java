package com.cheffi.user.constant;

public enum RoleType {

	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN"),
	GUEST("ROLE_GUEST");

	private final String authority;

	RoleType(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return this.authority;
	}
}
