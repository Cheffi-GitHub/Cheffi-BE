package com.cheffi.event.event;

import com.cheffi.avatar.domain.Avatar;

import lombok.Getter;

@Getter
public class ReviewCreateEvent {

	private final Long writerId;
	private final String nickname;

	public ReviewCreateEvent(Avatar writer) {
		this.writerId = writer.getId();
		this.nickname = writer.getNickname();
	}

}
