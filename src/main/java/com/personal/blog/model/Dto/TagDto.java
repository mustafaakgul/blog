package com.personal.blog.model.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TagDto {

    @NotEmpty
    @Size(min = 2, max = 50)
    private String tagName;

    public TagDto() {
    }

    public TagDto(@NotEmpty @Size(min = 2, max = 50) String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
