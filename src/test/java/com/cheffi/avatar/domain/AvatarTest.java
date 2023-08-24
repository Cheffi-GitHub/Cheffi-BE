package com.cheffi.avatar.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.common.constant.Address;
import com.cheffi.user.domain.User;

@ExtendWith(MockitoExtension.class)
class AvatarTest {

	private static final String NICKNAME = "Nick";
	private static final String PROVINCE = "서울시";
	private static final String CITY = "종로구";
	@Mock
	private User user;

	private final Address address = Address.cityAddress(PROVINCE, CITY);
	@Mock
	private Address childAddress;

	private Avatar avatar;

	@BeforeEach
	void setUp() {
		avatar = new Avatar(NICKNAME, user);
	}

	@Test
	@DisplayName("주소를 단순한 주소(Address)를 주지 않으면 예외가 발생한다.")
	void givenDetailedAddress() {
		given(childAddress.isSimpleAddress()).willReturn(false);
		assertThrows(IllegalArgumentException.class, () ->
			avatar.changeAddress(childAddress)
		);
	}

	@Test
	@DisplayName("주소가 단순한 주소일 경우 변경에 성공한다.")
	void givenSimpleAddress() {
		assertDoesNotThrow(() -> avatar.changeAddress(address));
		assertThat(avatar.getAddress()).isEqualTo(address);
	}

	@Nested
	@DisplayName("changeNickname 메서드")
	class changeNickname {

		private static final String NULL_STRING = null;
		private static final String BLANK_STRING = "";
		private static final String LENGTH_OVER8 = RandomStringUtils.random(10);
		private static final String VALID_NICKNAME = RandomStringUtils.random(6);

		@Test
		@DisplayName("Null인 닉네임이 주어지면 예외를 던진다.")
		void givenNullNickname() {
			assertThrows(BusinessException.class,
				() -> avatar.changeNickname(NULL_STRING));
		}

		@Test
		@DisplayName("빈 문자열인 닉네임이 주어지면 예외를 던진다.")
		void givenBlankNickname() {
			assertThrows(BusinessException.class,
				() -> avatar.changeNickname(BLANK_STRING));
		}

		@Test
		@DisplayName("8자 초과 닉네임이 주어지면 예외를 던진다.")
		void givenTooLongNickname() {
			assertThrows(BusinessException.class,
				() -> avatar.changeNickname(LENGTH_OVER8));
		}

		@Test
		@DisplayName("규칙에 부합하는 닉네임이 주어지면 변경에 성공한다.")
		void givenValidNickname() {
			avatar.changeNickname(VALID_NICKNAME);
			assertThat(avatar.getNickname()).isEqualTo(VALID_NICKNAME);
		}

	}

}
