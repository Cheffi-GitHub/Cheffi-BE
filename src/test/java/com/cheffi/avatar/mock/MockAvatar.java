package com.cheffi.avatar.mock;

import java.lang.reflect.Field;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.user.domain.User;

public class MockAvatar extends Avatar {

	public MockAvatar(Long id, String nickname, User user) {
		try {
			Field idField = Avatar.class.getDeclaredField("id");
			idField.setAccessible(true);
			idField.set(this, id);

			Field nicknameField = Avatar.class.getDeclaredField("nickname");
			nicknameField.setAccessible(true);
			nicknameField.set(this, nickname);

			Field userField = Avatar.class.getDeclaredField("user");
			userField.setAccessible(true);
			userField.set(this, user);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
