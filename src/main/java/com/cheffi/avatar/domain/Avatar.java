package com.cheffi.avatar.domain;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.common.constant.Address;
import com.cheffi.common.domain.BaseTimeEntity;
import com.cheffi.user.domain.User;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Avatar extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, max = 8)
	@NotNull
	private String nickname;
	private String introduction;
	@Embedded
	private Address address;
	private int cheffiCoinCnt;
	private int pointCnt;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "photo_id")
	private ProfilePhoto photo;

	public Avatar(String nickname, User user) {
		this.nickname = nickname;
		this.user = user;
		this.cheffiCoinCnt = 0;
		this.pointCnt = 0;
	}

	public void changeAddress(Address address) {
		if (!address.isSimpleAddress())
			throw new IllegalArgumentException("Address 클래스의 서브 클래스는 사용할 수 없습니다.");
		this.address = address;
	}

	public void changeNickname(String nickname) {
		if (!StringUtils.hasText(nickname))
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		if (nickname.length() < 2 || nickname.length() > 8)
			throw new BusinessException(ErrorCode.INVALID_NICKNAME_LENGTH);
		this.nickname = nickname;
	}

	public void changePhoto(ProfilePhoto photo) {
		Assert.notNull(photo, "변경할 사진은 null 일 수 없습니다.");
		this.photo = photo;
	}

	public boolean hasPhoto() {
		return this.getPhoto() != null;
	}
}
