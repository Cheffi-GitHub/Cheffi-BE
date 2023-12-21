package com.cheffi.notification.constant;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true,
	description = """
		* `REVIEW` - 리뷰 관련 알림
				
		* `BOOKMARK` - 찜 관련 알림
				
		* `FOLLOW` - 팔로우 관련 알림
				
		* `NOTICE` - 공식 게시글 알림
		""")
public enum NotificationCategory {
	REVIEW, BOOKMARK, FOLLOW, NOTICE
}
