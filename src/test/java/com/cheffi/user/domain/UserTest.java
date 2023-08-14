package com.cheffi.user.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cheffi.user.dto.UserCreateRequest;
import com.cheffi.user.constant.UserType;

@ExtendWith(MockitoExtension.class)
class UserTest {

	public User user;
	public static final Role ADMIN = new Role("ADMIN");
	public static final Role USER = new Role("USER");
	public static final Role GUEST = new Role("GUEST");
	public static final List<Role> ROLES = List.of(USER, GUEST);
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




	@Nested
	@DisplayName("createUser 메서드")
	class CreateUser {

		@Spy
		private UserCreateRequest createRequest;

		@BeforeEach
		void setUp() {
			// lenient() 메서드는 스터빙이 호출되지 않아서 생기는 Unnecessary Stubbing Exception 예외를 방지해준다.
			lenient().when(createRequest.email()).thenReturn(EMAIL);
			lenient().when(createRequest.name()).thenReturn(NAME);
			lenient().when(createRequest.userType()).thenReturn(USER_TYPE);
			lenient().when(createRequest.roles()).thenReturn(ROLES);
		}

		@Test
		@DisplayName("패스워스가 없어도 성공한다.")
		void givenNoPasswordWhenCreateUserThenSuccess() {
			//given
			given(createRequest.password()).willReturn(null);

			//when
			User newUser = User.createUser(createRequest);

			//then
			assertThat(newUser.getEmail()).isEqualTo(EMAIL);
			assertThat(newUser.getName()).isEqualTo(NAME);
			assertThat(newUser.getUserType()).isEqualTo(USER_TYPE);
		}

		@Test
		@DisplayName("이메일이 빈 문자열이면 예외를 던진다")
		void givenBlankOrNullEmailWhenCreateUserThenSuccess() {
			given(createRequest.email()).willReturn("");

			assertThatThrownBy(() -> User.createUser(createRequest))
				.isInstanceOf(IllegalArgumentException.class);
		}
		@Test
		@DisplayName("이메일이 null 이면 예외를 던진다")
		void givenNullEmailWhenCreateUserThenSuccess() {
			given(createRequest.email()).willReturn(null);

			assertThatThrownBy(() -> User.createUser(createRequest))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		@DisplayName("이름이 빈 문자열이면 예외를 던진다")
		void givenBlankOrNullNameWhenCreateUserThenSuccess() {
			given(createRequest.name()).willReturn("");

			assertThatThrownBy(() -> User.createUser(createRequest))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		@DisplayName("이름이 null 이면 예외를 던진다")
		void givenNullNameWhenCreateUserThenSuccess() {
			given(createRequest.name()).willReturn(null);

			assertThatThrownBy(() -> User.createUser(createRequest))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		@DisplayName("유저타입이 null 이면 예외를 던진다.")
		void givenNullUserTypeWhenCreateUserThenSuccess() {
			given(createRequest.userType()).willReturn(null);

			assertThatThrownBy(() -> User.createUser(createRequest))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		@DisplayName("유저의 Role 리스트가 비어있이면 예외를 던진다.")
		void givenEmptyRoleWhenCreateUserThenSuccess() {
			given(createRequest.roles()).willReturn(List.of());

			assertThatThrownBy(() -> User.createUser(createRequest))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		@DisplayName("유저의 Role 리스트가 null 이면 예외를 던진다.")
		void givenNullRoleWhenCreateUserThenSuccess() {
			given(createRequest.roles()).willReturn(null);

			assertThatThrownBy(() -> User.createUser(createRequest))
				.isInstanceOf(IllegalArgumentException.class);
		}

	}




}
