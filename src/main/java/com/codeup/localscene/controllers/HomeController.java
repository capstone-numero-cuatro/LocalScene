package com.codeup.localscene.controllers;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.Users;
import com.codeup.localscene.repositories.PostRepository;
import com.codeup.localscene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.stereotype.Controller;

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

    return"home";}

//    @GetMapping("/posts")
//    public String showPosts(Model model){
//        List<Posts> posts =postRepository.findAll();
//        model.addAttribute("posts", posts);
//
//        return "posts";
//
//    }


}
