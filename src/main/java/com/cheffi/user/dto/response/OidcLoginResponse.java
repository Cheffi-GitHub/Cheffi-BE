package com.cheffi.user.dto.response;

import com.cheffi.oauth.model.AuthenticationToken;
import com.cheffi.oauth.model.UserPrincipal;

public record OidcLoginResponse(
	UserPrincipal user
) {

	public OidcLoginResponse(AuthenticationToken token) {
		this((UserPrincipal)token.getPrincipal());
	}
}
