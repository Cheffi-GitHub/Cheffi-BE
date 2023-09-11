package com.cheffi.avatar.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.domain.Follow;
import com.cheffi.avatar.dto.response.AddFollowResponse;
import com.cheffi.avatar.mock.MockAvatar;
import com.cheffi.avatar.mock.MockFollow;
import com.cheffi.avatar.mock.MockUser;
import com.cheffi.avatar.repository.AvatarRepository;
import com.cheffi.avatar.repository.FollowRepository;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.common.config.exception.business.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {


	@Mock
	private AvatarRepository avatarRepository;
	@Mock
	private FollowRepository followRepository;

	@InjectMocks
	private FollowService followService;

	private MockAvatar follower;
	private MockAvatar followee;
	private static long FOLLOWER_ID = 1L;
	private static long FOLLOWEE_ID = 2L;

	@BeforeEach void setUp() {

		follower = new MockAvatar(FOLLOWER_ID,"아데산야", new MockUser());
		followee = new MockAvatar(FOLLOWEE_ID, "짱구", new MockUser());
	}

	@Nested
	@DisplayName("addFollow 메서드")
	class addFollow{

		@Test
		@DisplayName("follow 등록 성공")
		void successAddFollow() {

			when(avatarRepository.findById(FOLLOWER_ID)).thenReturn(Optional.of(follower));
			when(avatarRepository.findById(FOLLOWEE_ID)).thenReturn(Optional.of(followee));
			when(followRepository
				.findBySubjectAndTarget(any(Avatar.class), any(Avatar.class)))
				.thenReturn(Optional.empty());
			when(followRepository.save(any(Follow.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

			AddFollowResponse response = followService.addFollow(FOLLOWER_ID, FOLLOWEE_ID);

			assertEquals(1L, response.followerId());
			assertEquals(2L, response.followeeId());
		}

		@Test
		@DisplayName("등록실패 - 존재하지 않는 아바타")
		void failAddFollow_AVATAR_NOT_EXISTS() {

			when(avatarRepository.findById(FOLLOWER_ID)).thenReturn(Optional.of(follower));
			when(avatarRepository.findById(FOLLOWEE_ID)).thenReturn(Optional.empty());

			assertThrows(EntityNotFoundException.class, () -> {
				followService.addFollow(FOLLOWER_ID, FOLLOWEE_ID);
			});
		}

		@Test
		@DisplayName("등록실패 - 이미 팔로우 중인 아바타")
		void failAddFollow_ALREADY_FOLLOWED() {

			when(avatarRepository.findById(FOLLOWER_ID)).thenReturn(Optional.of(follower));
			when(avatarRepository.findById(FOLLOWEE_ID)).thenReturn(Optional.of(followee));
			when(followRepository.findBySubjectAndTarget(follower, followee))
				.thenReturn(Optional.of(mock(Follow.class)));

			assertThrows(RuntimeException.class, () -> {
				followService.addFollow(FOLLOWER_ID, FOLLOWEE_ID);
			});
		}
	}
}