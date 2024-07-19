package com.cheffi.avatar.domain;

import com.cheffi.common.domain.ImageFile;
import com.cheffi.file.domain.SinglePhoto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProfilePhoto implements SinglePhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@NotNull
	@Embedded
	private ImageFile file;


	public ProfilePhoto(ImageFile file) {
		this.file = file;
	}


}
