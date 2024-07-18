package com.cheffi.file.service;

import com.cheffi.common.domain.ImageFile;
import com.cheffi.file.constant.FilePath;
import com.cheffi.file.domain.SinglePhoto;
import com.cheffi.file.domain.SinglePhotoContainer;
import com.cheffi.file.factory.SinglePhotoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional
@Service
public class SinglePhotoService {

	private final ImageUploadService imageUploadService;

	public <T extends SinglePhotoContainer<P>, P extends SinglePhoto> void changePhoto(
			MultipartFile imageToChange, T container, SinglePhotoFactory<P> singlePhotoFactory, FilePath path) {
		removePhoto(container);
		addPhoto(imageToChange, container, singlePhotoFactory, path);
	}

	private <T extends SinglePhotoContainer<P>, P extends SinglePhoto> void addPhoto(MultipartFile image,
		T container, SinglePhotoFactory<P> singlePhotoFactory, FilePath path) {
		ImageFile file = imageUploadService.uploadImage(image, path, container.getId());
		container.changePhoto(singlePhotoFactory.convert(file));
	}

	private <T extends SinglePhotoContainer<P>, P extends SinglePhoto> void removePhoto(T container) {
		if (!container.getPhoto().isUploadedImage())
			return;
		String key = container.getKey();
		imageUploadService.removeFile(key);
	}

	public <T extends SinglePhotoContainer<P>, P extends SinglePhoto> void changePhoto(T container, P photoToChange) {
		removePhoto(container);
		container.changePhoto(photoToChange);
	}

}
