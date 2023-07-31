package com.cheffi.avatar.service;

import org.springframework.stereotype.Service;

import com.cheffi.avatar.dto.TagsChangeRequest;
import com.cheffi.avatar.dto.TagsChangeResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AvatarService {
	public TagsChangeResponse changeTags(Long avatarId, TagsChangeRequest tagsChangeRequest) {
		return new TagsChangeResponse(tagsChangeRequest.addTags(), tagsChangeRequest.removeTags());
	}
}
