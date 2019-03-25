package com.personal.blog.controller;

import com.personal.blog.model.Dto.TagDto;
import com.personal.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "/tags/add")
    public ModelAndView tagAddPage(){
        return new ModelAndView("addTag", "tagForm", new TagDto());
    }

    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public String handleTagAdd(@Valid @ModelAttribute("tagForm") TagDto form, BindingResult bindingResult, Principal principle) {
        if (bindingResult.hasErrors())
            return "addTag";
        tagService.saveTag(form);
        return "redirect:/tags";
    }

    @RequestMapping("/tags")
    public ModelAndView getTagPage() { return new ModelAndView("tags", "tags", tagService.findAllTags());
    }
}
