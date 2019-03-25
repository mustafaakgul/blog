package com.personal.blog.controller;

import com.personal.blog.model.Dto.PostAddDto;
import com.personal.blog.model.Dto.TagAssignDto;
import com.personal.blog.service.PostService;
import com.personal.blog.service.TagService;
import com.personal.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PostControllerNew {

    private final PostService postService;
    private final UserService userService;
    private final TagService tagService;

    @Autowired
    public PostControllerNew(PostService postService, UserService userService, TagService tagService) {
        this.postService = postService;
        this.userService = userService;
        this.tagService = tagService;
    }

    /**@RequestMapping(value = "/posts/add", method = RequestMethod.GET)
    public ModelAndView postAddPage(){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("tagsFromController", new TagAssignDto());
        model.put("tagNames", tagService.getTags());
        model.put("postFormNew", new PostAddDto());
        return new ModelAndView("addPost", "postFormNew", new PostAddDto());
    }*/

    @RequestMapping(value = "/posts/add", method = RequestMethod.GET)
    public String postAddPage(Model modelinView){
        //Map<String, Object> model = new HashMap<String, Object>();
        modelinView.addAttribute("tagsFromController", new TagAssignDto());
        modelinView.addAttribute("tagNames", tagService.getTags());
        modelinView.addAttribute("postFormNew", new PostAddDto());

        return "addPost";

        //model.put("tagsFromController", new TagAssignDto());
        //model.put("tagNames", tagService.getTags());
        //model.put("postFormNew", new PostAddDto());
        //return new ModelAndView("addPost", "postFormNew", new PostAddDto());
    }

    @RequestMapping(value = "/posts/add", method = RequestMethod.POST)
    public String handlePostAdd(@Valid @ModelAttribute("postFormNew") PostAddDto form,
                                @Valid @ModelAttribute("tagsFromController") TagAssignDto tagAssignDto,
                                BindingResult bindingResult,
                                Principal principle,
                                Model model) {
        if (bindingResult.hasErrors())
            return "addPost";
        postService.addPost(form, principle.getName(), tagAssignDto);
        //postService.addPost(form, principle.getName());

        return "redirect:/posts";
    }
}
