package com.cheffi.oauth.model;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.cheffi.user.domain.User;

public class AuthenticationToken extends AbstractAuthenticationToken {

	private final UserPrincipal principal;
	private String idToken;

	public AuthenticationToken(
		UserPrincipal principal,
		String idToken,
		Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		super.setAuthenticated(true);
		this.principal = principal;
		this.idToken = idToken;
	}

	@Override
	public Object getCredentials() {
		return idToken;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	public static AuthenticationToken of(User user, String idToken,
		Collection<? extends GrantedAuthority> authorities) {
		return new AuthenticationToken(UserPrincipal.of(user, authorities), idToken, authorities);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AuthenticationToken that))
			return false;
		if (!super.equals(o))
			return false;
		return Objects.equals(idToken, that.idToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), idToken);
	}
}
