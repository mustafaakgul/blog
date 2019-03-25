package com.personal.blog.controller;

import com.personal.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {


    @Autowired
    private TagRepository tagRepository;

    @RequestMapping("/test")
    public ModelAndView getItemsPage() {

        return new ModelAndView("test", "tags", tagRepository.findAll());
    }




}
