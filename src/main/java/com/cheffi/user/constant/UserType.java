package com.cheffi.user.constant;

public enum UserType {

	LOCAL, KAKAO;

	public static UserType from(String provider) {
		return UserType.valueOf(provider.toUpperCase());
	}

}
