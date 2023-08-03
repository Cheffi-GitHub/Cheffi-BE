package com.cheffi.avatar.service;

import org.springframework.stereotype.Service;

import com.cheffi.avatar.dto.request.TagsChangeRequest;
import com.cheffi.avatar.dto.response.SelfAvatarInfoResponse;
import com.cheffi.avatar.dto.response.TagsChangeResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AvatarService {
	public TagsChangeResponse changeTags(Long avatarId, TagsChangeRequest tagsChangeRequest) {
		return new TagsChangeResponse(tagsChangeRequest.addTags(), tagsChangeRequest.removeTags());
	}

	public SelfAvatarInfoResponse getSelfAvatarInfo(Long avatarId) {
		return SelfAvatarInfoResponse.mock();
	}
}
