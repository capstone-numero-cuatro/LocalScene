package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Posts;
import com.codeup.localscene.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    public HomeController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private PostRepository postRepository;

    @GetMapping("/home")
    public String welcome(Model model){
        List<Posts> posts =postRepository.findAll();
        model.addAttribute("posts", posts);

        return "home";}




}
