package com.cheffi.oauth.service;

import com.cheffi.oauth.dto.IdTokenAttributes;
import com.cheffi.oauth.model.OAuthAttributes;

public interface OidcLoginApiService {
	IdTokenAttributes verify(String token, String platform);

	OAuthAttributes getUserInfo(IdTokenAttributes idTokenAttributes);
}
