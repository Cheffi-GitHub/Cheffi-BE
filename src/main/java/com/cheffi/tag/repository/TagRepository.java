package com.cheffi.tag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheffi.tag.constant.TagType;
import com.cheffi.tag.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	List<Tag> findByTagType(TagType type);
}
