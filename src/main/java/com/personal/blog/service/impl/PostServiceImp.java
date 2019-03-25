package com.personal.blog.service.impl;

import com.personal.blog.model.Dto.PostAddDto;
import com.personal.blog.model.Dto.TagAssignDto;
import com.personal.blog.model.Post;
import com.personal.blog.model.Tag;
import com.personal.blog.model.User;
import com.personal.blog.repository.PostRepository;
import com.personal.blog.service.PostService;
import com.personal.blog.service.TagService;
import com.personal.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final TagService tagService;
    private final UserService userService;

    @Autowired
    public PostServiceImp(PostRepository postRepository, TagService tagService, UserService userService) {
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    public Optional<Post> findForId(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public Page<Post> findByUserOrderedByDatePageable(User user, int page) {
        return postRepository.findByUserOrderByCreateDateDesc(user, new PageRequest(subtractPageByOne(page), 5));
    }

    @Override
    public Page<Post> findAllOrderedByDatePageable(int page) {
        return postRepository.findAllByOrderByCreateDateDesc(new PageRequest(subtractPageByOne(page), 5));
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public List<Post> findAllByTags(List<Tag> tags) {
        return postRepository.findAllByTags(tags);
    }

    @Override
    public Optional<Post> getPostById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post assignPost(String tagName, long postId) {
        /**Tag tag = tagService.getTagByTagname(tagName);
        Post post = getPostById(postId);
        List<Post> postList = tag.getPost();
        postList.add(post);
        tag.setPost(postList);
        post.setTags(tag);
        return postRepository.save(post);*/
        return null;
    }

    @Override
    public void addPost(PostAddDto form, String user, TagAssignDto tagAssignDto) {

        Tag tag = tagService.getTagByTagname(tagAssignDto.getTagName());
        List<Tag> tags  = new ArrayList<>();
        tags.add(tag);

        Post post = new Post(form.getTitle(), form.getBody());
        post.setUser(userService.getUserByUsername(user).get());
        post.setTags(tags);
        postRepository.save(post);
        System.out.println(postRepository.findById(post.getId()));
    }

    @Override
    public void addPost(PostAddDto form, String user) {

        Post post = new Post(form.getTitle(), form.getBody());
        post.setUser(userService.getUserByUsername(user).get());
        postRepository.save(post);
        System.out.println(postRepository.findById(post.getId()));
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}
