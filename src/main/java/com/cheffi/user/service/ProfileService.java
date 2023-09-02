package com.cheffi.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.service.AvatarService;
import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProfileService {

	private final UserService userService;
	private final AvatarService avatarService;

	@Transactional
	public String completeProfile(Long userId, Long avatarId) {
		if (!userService.hasNoProfileRole(userId))
			throw new BusinessException(ErrorCode.PROFILE_ALREADY_REGISTERED);
		if (!avatarService.checkIfCompleteProfile(avatarId))
			throw new BusinessException(ErrorCode.PROFILE_NOT_COMPLETED);

		userService.removeNoProfileRole(userId);
		return "프로필 등록에 성공했습니다.";
	}
}
