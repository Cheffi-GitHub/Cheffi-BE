package com.cheffi.common.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;

@Service
public class ImagePathStrategy {

	private static final String PREFIX = "Cheffi_Photo_";

	private final DateTimeFormatter formatter;

	public ImagePathStrategy() {
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
	}

	public String getType(MultipartFile multipartFile) {
		File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		String mimeType;
		try {
			mimeType = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			throw new BusinessException(e.getMessage());
		}
		if (!mimeType.startsWith("image"))
			throw new BusinessException(ErrorCode.NOT_IMAGE_FILE);

		return mimeType.split("/")[1];
	}

	public String getFileName() {
		return UUID.randomUUID() + "/" + PREFIX + LocalDateTime.now().format(formatter);
	}

}
