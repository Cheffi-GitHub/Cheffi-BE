package com.cheffi.file.factory;


import com.cheffi.common.domain.ImageFile;
import com.cheffi.file.domain.SinglePhoto;

@FunctionalInterface
public interface SinglePhotoFactory<P extends SinglePhoto> {

	P convert(ImageFile file);

}
