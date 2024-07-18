package com.cheffi.file.service;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.FileUploadException;
import com.cheffi.file.constant.FilePath;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.UUID;

@Service
public class FilePathStrategy {

	private static final String PREFIX = "Map2Zero_";

	public String getType(MultipartFile multipartFile) {
		File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		String mimeType;
		try {
			mimeType = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			throw new FileUploadException(ErrorCode.FAIL_TO_GET_TYPE_OF_IMAGE);
		}
		if (!mimeType.startsWith("image"))
			throw new FileUploadException(ErrorCode.NOT_IMAGE_FILE);

		return mimeType.split("/")[1];
	}

	public String getPathOf(MultipartFile image, FilePath path, Object id) {
		return path.getPath(id) + "/" + PREFIX + path.name() + "_" + UUID.randomUUID() + "." + getType(image);
	}

}
