package com.cheffi.oauth.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.AuthenticationException;
import com.cheffi.oauth.dto.IdTokenAttributes;
import com.cheffi.oauth.model.AuthenticationToken;
import com.cheffi.oauth.model.OAuthAttributes;
import com.cheffi.user.domain.User;
import com.cheffi.user.dto.request.OidcLoginRequest;
import com.cheffi.user.dto.response.OidcLoginResponse;
import com.cheffi.user.service.RoleService;
import com.cheffi.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuthService {
	private final UserService userService;
	private final RoleService roleService;
	private final Map<String, OidcLoginApiService> providerMap;

	private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

	private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

	@Transactional
	public OidcLoginResponse oidcLogin(OidcLoginRequest loginRequest, String provider, HttpServletRequest request,
		HttpServletResponse response) {
		OidcLoginApiService apiService = providerMap.get(provider.toLowerCase());
		if (apiService == null)
			throw new AuthenticationException(ErrorCode.NOT_SUPPORTED_OAUTH_PROVIDER);

		// ID 토큰 검증
		IdTokenAttributes attributes = apiService.verify(loginRequest.token(), loginRequest.platform().toString());

		// 사용자 정보 가져오기
		OAuthAttributes oAuthAttributes = apiService.getUserInfo(attributes);

		User user = userService.findByEmail(oAuthAttributes.email()).orElseGet(() -> signUp(oAuthAttributes));

		Set<GrantedAuthority> authorities = getAuthoritiesFromUser(user);
		AuthenticationToken authenticationToken = AuthenticationToken.of(user, loginRequest.token(), authorities);

		saveToSecurityContext(request, response, authenticationToken);

		return new OidcLoginResponse(authenticationToken);
	}

	private void saveToSecurityContext(HttpServletRequest request, HttpServletResponse response,
		AuthenticationToken authenticationToken) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authenticationToken);
		securityContextHolderStrategy.setContext(context);
		securityContextRepository.saveContext(context, request, response);
	}

	private User signUp(OAuthAttributes oAuthAttributes) {
		return userService.signUp(oAuthAttributes
			.toUserCreateRequest(List.of(roleService.getUserRole())));
	}

	private Set<GrantedAuthority> getAuthoritiesFromUser(User user) {
		return user.getUserRoles()
			.stream()
			.map(ur ->
				new SimpleGrantedAuthority(ur.getRole().getAuthority()))
			.collect(Collectors.toSet());
	}

}
