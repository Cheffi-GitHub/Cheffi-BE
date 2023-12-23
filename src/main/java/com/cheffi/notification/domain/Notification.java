package com.cheffi.notification.domain;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.common.domain.BaseEntity;
import com.cheffi.notification.constant.NotificationCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Notification extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String content;

	@NotNull
	@Enumerated(EnumType.STRING)
	private NotificationCategory category;

	private boolean checked;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;

	private Notification(String content, NotificationCategory category, Avatar avatar) {
		this.content = content;
		this.category = category;
		this.avatar = avatar;
		this.checked = false;
	}

	public boolean isUnchecked() {
		return !this.checked;
	}

	public void check() {
		this.checked = true;
	}
}
