package com.cheffi.file.event;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteFileEvent {

	private final List<String> s3keysForBackup;

	public DeleteFileEvent(List<String> s3keysForBackup) {
		this.s3keysForBackup = s3keysForBackup;
	}

}
