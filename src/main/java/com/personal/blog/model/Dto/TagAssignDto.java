package com.personal.blog.model.Dto;

public class TagAssignDto {

    private String tagName;

    public TagAssignDto() {
    }

    public TagAssignDto(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
