package com.cheffi.web.kakao.dto;

public record OidcPublicKeyDto(
	String kid,
	String alg,
	String use,
	String n,
	String e
) {
}
