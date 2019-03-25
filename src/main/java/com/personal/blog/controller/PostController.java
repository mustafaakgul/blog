package com.personal.blog.controller;

import com.personal.blog.model.Dto.PostAddDto;
import com.personal.blog.model.Dto.TagAssignDto;
import com.personal.blog.model.Dto.TagDto;
import com.personal.blog.model.Post;
import com.personal.blog.model.User;
import com.personal.blog.service.PostService;
import com.personal.blog.service.TagService;
import com.personal.blog.service.UserService;
import com.personal.blog.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final TagService tagService;

    @Autowired
    public PostController(PostService postService, UserService userService, TagService tagService) {
        this.postService = postService;
        this.userService = userService;
        this.tagService = tagService;
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String newPost(Principal principal,
                          Model model) {

        Optional<User> user = userService.findByUsername(principal.getName());

        if (user.isPresent()) {
            Post post = new Post();
            post.setUser(user.get());

            model.addAttribute("post", post);

            return "/postForm";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String createNewPost(@Valid Post post,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/postForm";
        } else {
            postService.save(post);
            return "redirect:/blog/" + post.getUser().getUsername();
        }
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id,
                                 Principal principal,
                                 Model model) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("post", post);
                return "/postForm";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String getPostWithId(@PathVariable Long id,
                                Principal principal,
                                Model model) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            model.addAttribute("post", post);
            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("username", principal.getName());
            }

            return "/post";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id,
                                   Principal principal) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipalOwnerOfPost(principal, post)) {
                postService.delete(post);
                return "redirect:/home";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getUser().getUsername());
    }
}
