package com.cheffi.user.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.cheffi.oauth.model.AuthenticationToken;
import com.cheffi.oauth.model.UserPrincipal;
import com.cheffi.user.constant.UserType;

import lombok.Builder;

@Builder
public record OidcLoginResponse(
	String email,
	boolean locked,
	boolean expired,
	boolean activated,
	LocalDateTime lastPwChangedDate,
	String name,
	UserType userType,
	boolean adAgreed,
	boolean analysisAgreed,
	Long avatarId,
	String nickname,
	List<GrantedAuthority> authorities

) {

	public static OidcLoginResponse of(AuthenticationToken token) {
		UserPrincipal principal = (UserPrincipal)token.getPrincipal();
		return OidcLoginResponse.builder()
			.email(principal.getEmail())
			.locked(principal.isLocked())
			.expired(principal.isExpired())
			.activated(principal.isActivated())
			.lastPwChangedDate(principal.getLastPwChangedDate())
			.name(principal.getName())
			.userType(principal.getUserType())
			.adAgreed(principal.isAdAgreed())
			.analysisAgreed(principal.isAnalysisAgreed())
			.avatarId(principal.getAvatarId())
			.nickname(principal.getNickname())
			.authorities((new ArrayList<>(principal.getAuthorities())))
			.build();
	}
}
