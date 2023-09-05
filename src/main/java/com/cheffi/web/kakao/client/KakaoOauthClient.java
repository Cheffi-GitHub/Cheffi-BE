package com.cheffi.web.kakao.client;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cheffi.common.config.fegin.OpenFeignConfig;
import com.cheffi.web.kakao.dto.OidcPublicKeysResponse;

@FeignClient(
	name = "KakaoOauthClient",
	url = "https://kauth.kakao.com",
	configuration = OpenFeignConfig.class)
public interface KakaoOauthClient {
	@Cacheable(cacheNames = "KakaoKid", cacheManager = "oidcCacheManager")
	@GetMapping("/.well-known/jwks.json")
	OidcPublicKeysResponse getKakaoOidcOpenKeys();
}
