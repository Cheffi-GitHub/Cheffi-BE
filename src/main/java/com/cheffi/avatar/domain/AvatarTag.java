package com.cheffi.avatar.domain;

import com.cheffi.common.domain.BaseTimeEntity;
import com.cheffi.tag.domain.Tag;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AvatarTag extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id")
	private Tag tag;

	private AvatarTag(Avatar avatar, Tag tag) {
		this.avatar = avatar;
		this.tag = tag;
	}

	public static AvatarTag mapTagToAvatar(Avatar avatar, Tag tag) {
		return new AvatarTag(avatar, tag);
	}

}
