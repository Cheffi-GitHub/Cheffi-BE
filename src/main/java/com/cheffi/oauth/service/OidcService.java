package com.cheffi.oauth.service;

import org.springframework.stereotype.Service;

import com.cheffi.oauth.dto.IdTokenAttributes;
import com.cheffi.web.kakao.dto.OidcPublicKeyDto;
import com.cheffi.web.kakao.dto.OidcPublicKeysResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OidcService {

	private final JwtManager jwtManager;

	private String getKidFromUnsignedIdToken(String token, String iss, String aud) {
		return jwtManager.getKidFromUnsignedTokenHeader(token, iss, aud);
	}

	public IdTokenAttributes getPayloadFromIdToken(
		String token, String iss, String aud, OidcPublicKeysResponse oidcPublicKeysResponse) {

		String kid = getKidFromUnsignedIdToken(token, iss, aud);

		OidcPublicKeyDto oidcPublicKeyDto =
			oidcPublicKeysResponse.keys().stream()
				.filter(o -> o.kid().equals(kid))
				.findFirst()
				.orElseThrow();

		return jwtManager.getOIDCTokenBody(token,
			oidcPublicKeyDto.n(),
			oidcPublicKeyDto.e());

	}

}
