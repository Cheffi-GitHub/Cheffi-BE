package com.cheffi.tag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cheffi.avatar.dto.common.TagDto;
import com.cheffi.tag.constant.TagType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {
	public List<TagDto> getAllTags(TagType type) {
		return TagDto.mock();
	}
}
