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

import com.cheffi.user.constant.RoleType;
import com.cheffi.user.constant.UserType;
import com.cheffi.user.dto.UserCreateRequest;

@ExtendWith(MockitoExtension.class)
class UserTest {

	public User user;
	public static final Role ROLE_ADMIN = new Role(RoleType.ADMIN);
	public static final Role ROLE_USER = new Role(RoleType.USER);
	public static final Role ROLE_GUEST = new Role(RoleType.GUEST);
	public static final List<Role> ROLES = List.of(ROLE_USER, ROLE_GUEST);
	public static final String USER_NAME = "홍길동";
	public static final String USER_EMAIL = "foo@naver.com";
	public static final UserType USER_TYPE = UserType.KAKAO;

	@BeforeEach
	void setUp() {
		user = User.builder()
			.name(USER_NAME)
			.email(USER_EMAIL)
			.activated(true)
			.withdrawn(false)
			.expired(false)
			.locked(false)
			.userType(USER_TYPE)
			.build();
	}

	@Nested
	@DisplayName("AddRoles 메서드")
	class AddRoles {

		@Test
		@DisplayName("Role 추가가 정상적으로 반영된다.")
		void givenRolesWhenAddRolesThenApplied() {
			//when
			user.addRoles(List.of(ROLE_ADMIN, ROLE_USER));

			//then
			List<UserRole> userRoles = user.getUserRoles();
			List<Role> roles = userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
			assertThat(userRoles).hasSize(2);
			assertThat(roles).contains(ROLE_ADMIN, ROLE_USER);
		}

		@Test
		@DisplayName("중복된 ROLE Entity 중복 매핑되지 않는다.")
		void givenDuplicatedRolesWhenAddRolesThenUnique() {
			//when
			user.addRoles(List.of(ROLE_ADMIN, ROLE_USER));
			user.addRoles(List.of(ROLE_ADMIN, ROLE_USER, ROLE_GUEST));
			user.addRoles(List.of(ROLE_ADMIN));
			user.addRoles(List.of(ROLE_ADMIN, ROLE_GUEST));

			//then
			List<UserRole> userRoles = user.getUserRoles();
			List<Role> roles = userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
			assertThat(userRoles).hasSize(3);
			assertThat(roles).contains(ROLE_ADMIN, ROLE_USER, ROLE_GUEST);
		}


	}


	@Nested
	@DisplayName("createUser 메서드")
	class CreateUser {

		@Spy
		private UserCreateRequest createRequest;

		@BeforeEach
		void setUp() {
			// lenient() 메서드는 스터빙이 호출되지 않아서 생기는 Unnecessary Stubbing Exception 예외를 방지해준다.
			lenient().when(createRequest.email()).thenReturn(USER_EMAIL);
			lenient().when(createRequest.name()).thenReturn(USER_NAME);
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
			assertThat(newUser.getEmail()).isEqualTo(USER_EMAIL);
			assertThat(newUser.getName()).isEqualTo(USER_NAME);
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
