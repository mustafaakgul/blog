package com.personal.blog.service.impl;

import com.personal.blog.model.Dto.TagDto;
import com.personal.blog.model.Post;
import com.personal.blog.model.Tag;
import com.personal.blog.repository.TagRepository;
import com.personal.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag save(Tag tag) {
        return null;
    }

    @Override
    public void saveTag(TagDto form) {

        Tag tag = new Tag(form.getTagName());
        tagRepository.save(tag);
        System.out.println(tagRepository.findById(tag.getId()));
    }

    @Override
    public Iterable<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Post> findAllPostsByTag(Tag tag) {

        return null;
    }

    @Override
    public List<String> getTags() {

        List<String> tagNames = new ArrayList<String>();
        Iterator iterator = findAllTags().iterator();
        while (iterator.hasNext()) {
            Tag tag = (Tag) iterator.next();
            tagNames.add(tag.getTag());
        }
        return tagNames;
    }

    @Override
    public Tag getTagByTagname(String tagName) {
        return tagRepository.findByTag(tagName);
    }
}
