package com.cheffi.file.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cheffi.file.constant.FilePath;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FilePathStrategy {

	private final FileTypeService fileTypeService;

	private static final String PREFIX = "Cheffi_";


	public String getPathOf(MultipartFile image, FilePath path, Object id) {
		return path.getPath(id) + "/" + PREFIX + path.name() + "_" + UUID.randomUUID() + "." + fileTypeService.getExtension(image);
	}

}
