package com.cheffi.file.event;

import lombok.Getter;

import java.util.List;

@Getter
public class UploadFileEvent {

	private final List<String> addedS3keys;

	public UploadFileEvent(List<String> addedS3keys) {
		this.addedS3keys = addedS3keys;
	}

}
