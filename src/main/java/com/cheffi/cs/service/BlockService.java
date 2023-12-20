package com.cheffi.cs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.service.AvatarService;
import com.cheffi.avatar.service.FollowService;
import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.cs.domain.Block;
import com.cheffi.cs.dto.PostBlockRequest;
import com.cheffi.cs.repository.BlockRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BlockService {

	private final BlockRepository blockRepository;
	private final AvatarService avatarService;
	private final FollowService followService;

	@Transactional
	public Long block(Long subjectId, PostBlockRequest request) {
		if (blockRepository.existsBySubjectAndTarget(subjectId, request.id()))
			throw new BusinessException(ErrorCode.ALREADY_BLOCKED);
		// 팔로우 관계 삭제
		followService.destroyFriendship(subjectId, request.id());
		return blockRepository.save(Block.of(avatarService.getById(subjectId), avatarService.getById(request.id())))
			.getId();
	}

}
