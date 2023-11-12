package com.cheffi.common.dto;

import java.util.List;
import java.util.function.Function;

import lombok.Getter;

@Getter
public class CursorPage<T, C> {
	private final List<T> data;
	private final int size;
	private final boolean hasNext;
	private final C first;
	private final C end;
	private final C next;

	// 다음이 남아 있을 때
	private CursorPage(List<T> data, int requestedSize, Function<T, C> cursorGetter, boolean hasNext) {
		this.first = cursorGetter.apply(data.get(0));
		if (hasNext) {
			this.size = requestedSize;
			this.next = cursorGetter.apply(data.get(this.size));
		} else {
			this.size = data.size();
			this.next = null;
		}
		this.hasNext = hasNext;
		this.end = cursorGetter.apply(data.get(this.size - 1));
		this.data = data.subList(0, this.size - 1);
	}

	private CursorPage(List<T> data) {
		this.data = data;
		this.size = 0;
		this.hasNext = false;
		this.first = null;
		this.end = null;
		this.next = null;
	}

	/**
	 *
	 * @param data
	 * 데이터는 반드시 사용자가 요청한 size + 1 만큼을 쿼리해서 넘겨줘야 합니다.
	 * @param requestedSize
	 * 사용자가 요청한 size
	 * @param cursorGetter
	 * 데이터로부터 커서를 가져오는 함수
	 * @return
	 * 1. 데이터가 빈 경우
	 * 2. 다음 데이터가 존재할 경우
	 * 3. 다음 데이터가 존재하지 않을 경우
	 * 각각 다른 결과가 반환됩니다.
	 * @param <T>
	 *     데이터의 타입
	 * @param <C>
	 *     커서의 타입
	 */

	public static <T, C> CursorPage<T, C> of(List<T> data, int requestedSize, Function<T, C> cursorGetter) {
		if (data.isEmpty())
			return new CursorPage<>(data);
		if (data.size() == requestedSize + 1)
			return new CursorPage<>(data, requestedSize, cursorGetter, true);
		return new CursorPage<>(data, requestedSize, cursorGetter, false);
	}

	public boolean isEmpty() {
		return this.getData().isEmpty();
	}

}
