package com.personal.blog.service;

import com.personal.blog.model.Dto.TagDto;
import com.personal.blog.model.Post;
import com.personal.blog.model.Tag;

import java.util.List;

public interface TagService {

    Tag save(Tag tag);

    void saveTag(TagDto form);

    Iterable<Tag> findAllTags();

    List<Post> findAllPostsByTag(Tag tag);

    List<String> getTags();

    Tag getTagByTagname(String tagName);
}
