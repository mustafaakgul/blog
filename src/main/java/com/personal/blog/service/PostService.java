package com.personal.blog.service;

import com.personal.blog.model.Dto.PostAddDto;
import com.personal.blog.model.Dto.TagAssignDto;
import com.personal.blog.model.Post;
import com.personal.blog.model.Tag;
import com.personal.blog.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findForId(Long id);

    Post save(Post post);

    Page<Post> findByUserOrderedByDatePageable(User user, int page);

    Page<Post> findAllOrderedByDatePageable(int page);

    void delete(Post post);

    List<Post> findAllByTags(List<Tag> tags);

    Optional<Post> getPostById(long id);

    Post assignPost(String tagName, long postId);

    void addPost(PostAddDto form, String user, TagAssignDto tagAssignDto);

    void addPost(PostAddDto form, String user);
}
