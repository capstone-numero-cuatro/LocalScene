package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Posts;
import com.codeup.localscene.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postRepository;

    @Autowired
    public PostController (PostRepository postRepository){
        this.postRepository = postRepository;
    };




    @GetMapping("/profile/{id}/posts")
    public String getPosts(Model model) {
        List<Posts> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("posts", new Posts());
        return "redirect:/posts";
    }

    //create post, saves post, redirects to list of posts
    @PostMapping("/profile/{id}/posts/create")
    public String createPosts(@ModelAttribute("posts") Posts posts) {
        postRepository.save(posts);
        return "redirect:/posts";
    }

    //delete
    @PostMapping("/profile/{id}/posts/delete")
    public String deletePosts(@ModelAttribute("posts") Posts posts){
        postRepository.delete(posts);
        return "redirect:/posts";
    }

}


