package com.cheffi.file.service;

import com.cheffi.file.dto.FileInfo;
import com.cheffi.file.dto.FileUploadRequest;

import java.util.List;

public interface FileStorageService {

	FileInfo uploadFile(FileUploadRequest request);

	List<FileInfo> uploadFiles(List<FileUploadRequest> requests);

	void deleteFile(String key);

	void deleteFiles(List<String> keys);

	void recoverBackups(List<String> backupKeys);

	void deleteBackups(List<String> backupKeys);

}
