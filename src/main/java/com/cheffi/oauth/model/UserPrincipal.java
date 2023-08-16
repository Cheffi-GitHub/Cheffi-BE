package com.cheffi.oauth.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cheffi.user.constant.UserType;
import com.cheffi.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class UserPrincipal implements UserDetails, Serializable {

	private final String email;
	private final boolean locked;
	private final boolean expired;
	private final boolean activated;
	private final LocalDateTime lastPwChangedDate;
	private final String name;
	private final UserType userType;
	private final boolean adAgreed;
	private final boolean analysisAgreed;
	private final String fcmToken;
	private final List<GrantedAuthority> authorities;

	public static UserPrincipal of(User user, Collection<? extends GrantedAuthority> authorities) {
		return UserPrincipal.builder()
			.email(user.getEmail())
			.locked(user.isLocked())
			.expired(user.isExpired())
			.activated(user.isActivated())
			.lastPwChangedDate(user.getLastPwChangedDate())
			.name(user.getName())
			.userType(user.getUserType())
			.adAgreed(user.isAdAgreed())
			.analysisAgreed(user.isAnalysisAgreed())
			.fcmToken(user.getFcmToken())
			.authorities(new ArrayList<>(authorities))
			.build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return activated;
	}
}
