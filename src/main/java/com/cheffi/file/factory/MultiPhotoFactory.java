package com.cheffi.file.factory;


import com.cheffi.common.domain.ImageFile;
import com.cheffi.file.domain.MultiPhoto;
import com.cheffi.file.domain.MultiPhotoContainer;

@FunctionalInterface
public interface MultiPhotoFactory<T extends MultiPhotoContainer<P>, P extends MultiPhoto> {

	P convert(ImageFile file, int order, T container);

}
