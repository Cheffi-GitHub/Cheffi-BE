package com.cheffi.user.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.cheffi.user.constant.UserType;

class UserTest {

	private User user;
	private static final Role ADMIN = new Role("ADMIN");
	private static final Role USER = new Role("USER");
	private static final Role GUEST = new Role("GUEST");
	public static final String NAME = "홍길동";
	public static final String EMAIL = "foo@naver.com";
	public static final UserType USER_TYPE = UserType.KAKAO;

	@BeforeEach
	void setUp() {
		user = User.builder()
			.name(NAME)
			.email(EMAIL)
			.activated(true)
			.withdrawn(false)
			.expired(false)
			.locked(false)
			.userType(USER_TYPE)
			.build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void givenRolesWhenAddRolesThenApplied() {
		//when
		user.addRoles(List.of(ADMIN, USER));

		//then
		List<UserRole> userRoles = user.getUserRoles();
		List<Role> roles = userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
		assertThat(userRoles).hasSize(2);
		assertThat(roles).contains(ADMIN, USER);
	}

	@Test
	void givenDuplicatedRolesWhenAddRolesThenUnique() {
		//when
		user.addRoles(List.of(ADMIN, USER));
		user.addRoles(List.of(ADMIN, USER, GUEST));
		user.addRoles(List.of(ADMIN));
		user.addRoles(List.of(ADMIN, GUEST));

		//then
		List<UserRole> userRoles = user.getUserRoles();
		List<Role> roles = userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
		assertThat(userRoles).hasSize(3);
		assertThat(roles).contains(ADMIN, USER, GUEST);
	}

	@Test
	void givenUserInfoWhenCreateUserThenSuccess() {
		//when
		User.createUser(EMAIL, NAME, USER_TYPE, List.of(USER));

		//then
		assertThat(user.getEmail()).isEqualTo(EMAIL);
		assertThat(user.getName()).isEqualTo(NAME);
		assertThat(user.getUserType()).isEqualTo(USER_TYPE);
	}

	@Test
	void givenBlankOrNullEmailWhenCreateUserThenSuccess() {
		assertThatThrownBy(() -> User.createUser("", NAME, USER_TYPE, List.of(USER)))
			.isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> User.createUser(null, NAME, USER_TYPE, List.of(USER)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void givenBlankOrNullNameWhenCreateUserThenSuccess() {
		assertThatThrownBy(() -> User.createUser(EMAIL, "", USER_TYPE, List.of(USER)))
			.isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> User.createUser(EMAIL, null, USER_TYPE, List.of(USER)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void givenNullUserTypeWhenCreateUserThenSuccess() {
		assertThatThrownBy(() -> User.createUser(EMAIL, NAME, null, List.of(USER)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void givenNullOrEmptyRoleWhenCreateUserThenSuccess() {
		assertThatThrownBy(() -> User.createUser(EMAIL, NAME, USER_TYPE, List.of()))
			.isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> User.createUser(EMAIL, NAME, USER_TYPE, null))
			.isInstanceOf(IllegalArgumentException.class);
	}


}
