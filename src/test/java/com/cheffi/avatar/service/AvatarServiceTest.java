package com.cheffi.avatar.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cheffi.avatar.repository.AvatarRepository;
import com.cheffi.common.config.exception.business.BusinessException;

@ExtendWith(MockitoExtension.class)
class AvatarServiceTest {

	public static final Long AVATAR_ID = 10L;

	@Mock
	private AvatarRepository avatarRepository;

	@InjectMocks
	private AvatarService avatarService;

	@Nested
	@DisplayName("updateNickname 메서드")
	class updateNickname {

		public static final String USING_NICKNAME = "이미사용중닉네임";
		public static final String INCLUDING_BANNED_WORDS = "나는쉐피";

		@Test
		@DisplayName("이미 사용 중인 닉네임이 주어지면 실패한다.")
		void givenAlreadyInUseNickname() {
			//given
			given(avatarRepository.existsByNickname(USING_NICKNAME)).willReturn(false);

			//then
			assertThrows(BusinessException.class, () ->
				//when
				avatarService.updateNickname(AVATAR_ID, USING_NICKNAME));
		}

		@Test
		@DisplayName("금지 단어를 포함한 닉네임이 주어지면 실패한다.")
		void givenNicknameIncludingBannedWords() {
			//then
			assertThrows(BusinessException.class, () ->
				//when
				avatarService.updateNickname(AVATAR_ID, INCLUDING_BANNED_WORDS));
		}

	}

}
