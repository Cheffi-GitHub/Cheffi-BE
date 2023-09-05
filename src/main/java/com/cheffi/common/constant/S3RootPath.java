package com.cheffi.common.constant;

import lombok.Getter;

@Getter
public enum S3RootPath {

	PROFILE_PHOTO("profile"), REVIEW_PHOTO("review"), TEST("test");

	private final String path;

	S3RootPath(String path) {
		this.path = path;
	}
}
