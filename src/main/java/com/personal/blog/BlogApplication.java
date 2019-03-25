package com.personal.blog;

import com.personal.blog.model.Tag;
import com.personal.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

    @Autowired
    TagRepository tagRepository;

    public static void main(String[] args) {

        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setTag("spring");
        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setTag("spring boot");
        Tag tag3 = new Tag();
        tag3.setId(3L);
        tag3.setTag("spring security");
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(tag3);
    }
}
