package com.cheffi.avatar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.domain.AvatarTag;
import com.cheffi.avatar.dto.common.TagDto;
import com.cheffi.avatar.dto.request.TagsChangeRequest;
import com.cheffi.avatar.dto.response.TagsChangeResponse;
import com.cheffi.avatar.repository.AvatarRepository;
import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.EntityNotFoundException;
import com.cheffi.tag.constant.TagType;
import com.cheffi.tag.domain.Tag;
import com.cheffi.tag.service.TagService;
import com.cheffi.tag.service.validation.TagValidationPolicy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AvatarTagService {

	private final AvatarRepository avatarRepository;
	private final TagService tagService;
	private final TagValidationPolicy tagValidationPolicy;

	@Transactional
	public TagsChangeResponse changeTags(Long avatarId, TagsChangeRequest request) {
		Avatar avatar = getByIdWithTags(avatarId);
		List<Tag> tagsFromAvatar = getTagsFromAvatar(avatar);

		List<Tag> requestedTags = tagService.getAllById(request.combinedList());
		tagValidationPolicy.validateTagsOfAvatar(requestedTags, request.foodTags(), request.tasteTags());

		List<Tag> tagsToAdd = tagService.extractDistinctTags(requestedTags, tagsFromAvatar);
		List<Tag> tagsToRemove = tagService.extractDistinctTags(tagsFromAvatar, requestedTags);

		avatar.addTags(tagsToAdd);
		avatar.removeTags(tagsToRemove);

		return new TagsChangeResponse(getTagDtoByType(avatar, TagType.FOOD), getTagDtoByType(avatar, TagType.TASTE));
	}

	private List<TagDto> getTagDtoByType(Avatar avatar, TagType type) {
		return avatar.getAvatarTags().stream()
			.map(AvatarTag::getTag)
			.filter(t -> t.getTagType().equals(type))
			.map(TagDto::of)
			.toList();
	}

	private List<Tag> getTagsFromAvatar(Avatar avatar) {
		return avatar.getAvatarTags().stream()
			.map(AvatarTag::getTag)
			.toList();
	}

	public Avatar getByIdWithTags(Long avatarId) {
		return avatarRepository.findByIdWithTags(avatarId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_EXISTS));
	}

}
