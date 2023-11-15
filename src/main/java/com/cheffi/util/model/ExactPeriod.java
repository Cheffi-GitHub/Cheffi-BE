package com.cheffi.util.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import lombok.Getter;

/**
 * 시간 단위로 기간을 나타내는 객체
 */
@Getter
public class ExactPeriod {

	private final LocalDateTime start;
	private final LocalDateTime end;
	private final Duration duration;

	/**
	 * @param end      기준이 되는 끝 시간
	 *                 분, 초 정보는 정각을 다루기 때문에 버려진다.
	 * @param duration Duration 은 시간 단위로 입력해야 정상적으로 동작한다.
	 */

	public ExactPeriod(LocalDateTime end, Duration duration) {
		// 받은 시간을 정각으로 변환
		end = end.truncatedTo(ChronoUnit.HOURS);
		this.start = end.minus(duration);
		this.end = end;
		this.duration = duration;
	}
}
