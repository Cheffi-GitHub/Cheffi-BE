package com.cheffi.common.dev;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.cheffi.tag.constant.TagType;
import com.cheffi.tag.domain.Tag;
import com.cheffi.tag.repository.TagRepository;
import com.cheffi.user.service.RoleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Profile({"local", "dev"})
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final RoleService roleService;
	private final TagRepository tagRepository;

	boolean alreadySetup = false;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;

		roleService.getUserRole();
		roleService.getNoProfileRole();

		tagRepository.save(new Tag(TagType.FOOD, "한식"));
		tagRepository.save(new Tag(TagType.FOOD, "중식"));
		tagRepository.save(new Tag(TagType.FOOD, "일식"));
		tagRepository.save(new Tag(TagType.FOOD, "양식"));
		tagRepository.save(new Tag(TagType.FOOD, "비건"));
		tagRepository.save(new Tag(TagType.FOOD, "분식"));

		tagRepository.save(new Tag(TagType.TASTE, "매콤한"));
		tagRepository.save(new Tag(TagType.TASTE, "자극적인"));
		tagRepository.save(new Tag(TagType.TASTE, "담백한"));
		tagRepository.save(new Tag(TagType.TASTE, "시원한"));
		tagRepository.save(new Tag(TagType.TASTE, "얼큰한"));
		tagRepository.save(new Tag(TagType.TASTE, "달달한"));

		alreadySetup = true;
	}
}
