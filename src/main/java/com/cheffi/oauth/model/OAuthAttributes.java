package com.cheffi.oauth.model;

import java.util.List;

import com.cheffi.user.constant.UserType;
import com.cheffi.user.domain.Role;
import com.cheffi.user.dto.UserCreateRequest;

import lombok.Builder;

//회원가입을 위한 클래스로, 각기 다른 플랫폼의 회원정보를 일원화시키기 위해서 사용
@Builder
public record OAuthAttributes(
	String email,
	String name,
	String profile,
	UserType userType
) {

	public UserCreateRequest toUserCreateRequest(List<Role> roles) {
		return UserCreateRequest.ofSocial(email, name, userType, roles);
	}

}
