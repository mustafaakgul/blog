package com.personal.blog.model.Dto;

import com.personal.blog.model.Tag;
import com.personal.blog.model.User;

import java.util.List;

public class PostAddDto {

    private String title;

    private String body;

    //private List<Tag> tags;


    public PostAddDto() {
    }

    public PostAddDto(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /**public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }*/
}

