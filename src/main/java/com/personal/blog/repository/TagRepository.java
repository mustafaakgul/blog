package com.personal.blog.repository;

import com.personal.blog.model.Post;
import com.personal.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    //Tag findByTag(@Param("tag") String tag);

    List<Post> findPostsByTag(String tag);

    List<Post> findPostByTag(String tag);

    Tag findByTag(String tag);
}
