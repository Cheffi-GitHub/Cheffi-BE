package com.cheffi.avatar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.dto.adapter.SelfAvatarInfo;
import com.cheffi.avatar.dto.request.TagsChangeRequest;
import com.cheffi.avatar.dto.response.AvatarInfoResponse;
import com.cheffi.avatar.dto.response.TagsChangeResponse;
import com.cheffi.avatar.repository.AvatarRepository;
import com.cheffi.common.aspect.annotation.UpdatePrincipal;
import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.common.config.exception.business.EntityNotFoundException;
import com.cheffi.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AvatarService {

	private final AvatarRepository avatarRepository;

	@UpdatePrincipal
	@Transactional
	public SelfAvatarInfo updateNickname(Long avatarId, String nickname) {
		if (nickname.contains("쉐피"))
			throw new BusinessException(ErrorCode.NICKNAME_CONTAINS_BANNED_WORDS);
		if (isNicknameInUse(nickname))
			throw new BusinessException(ErrorCode.NICKNAME_ALREADY_IN_USE);
		Avatar avatar = getById(avatarId);
		avatar.changeNickname(nickname);
		return SelfAvatarInfo.of(avatar);
	}

	public boolean isNicknameInUse(String nickname) {
		return avatarRepository.existsByNickname(nickname);
	}

	@UpdatePrincipal
	public SelfAvatarInfo getSelfAvatarInfo(Long avatarId) {
		Avatar avatar = getByIdWithPhoto(avatarId);
		return SelfAvatarInfo.of(avatar, avatar.getPhoto());
	}

	public Avatar getById(Long avatarId) {
		return avatarRepository.findById(avatarId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_EXISTS));
	}

	public Avatar getByUserWithPhoto(User user) {
		return avatarRepository.findByUserWithPhoto(user)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_EXISTS));
	}

	public Avatar getByIdWithPhoto(Long avatarId) {
		return avatarRepository.findByIdWithPhoto(avatarId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_EXISTS));
	}

	public AvatarInfoResponse getAvatarInfo(Long avatarId) {
		return AvatarInfoResponse.mock();
	}

	public TagsChangeResponse changeTags(Long avatarId, TagsChangeRequest tagsChangeRequest) {
		return new TagsChangeResponse(tagsChangeRequest.addTags(), tagsChangeRequest.removeTags());
	}

}
