package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.PostRepository;
import com.codeup.localscene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile/{id}/posts")
    public String getPosts(Model model) {
        List<Posts> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("posts", new Posts());
        return "redirect:/posts";
    }

    //delete
    @PostMapping("/profile/{id}/posts/{postId}/delete")

    public String deletePosts(@PathVariable("id") Long Id,
                              @PathVariable("postId") Long postId) {
        postRepository.deleteById(postId);

        return "redirect:/profile/{id}";

        }
    }

