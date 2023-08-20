package com.cheffi.avatar.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
		lenient().doNothing().when(user).setAvatar(avatar);
		avatar = new Avatar(NICKNAME, user);
	}

	@Test
	void givenDetailedAddress() {
		given(childAddress.isSimpleAddress()).willReturn(false);
		assertThrows(IllegalArgumentException.class, () ->
			avatar.changeAddress(childAddress)
		);
	}

	@Test
	void givenSimpleAddress() {
		assertDoesNotThrow(() -> avatar.changeAddress(address));
		assertThat(avatar.getAddress()).isEqualTo(address);
	}
}
