package com.cheffi.web.kakao.dto;

import java.util.List;

public record OidcPublicKeysResponse(
	List<OidcPublicKeyDto> keys
) {
}
