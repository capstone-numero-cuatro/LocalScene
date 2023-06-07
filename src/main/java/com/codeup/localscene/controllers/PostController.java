package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.Users;
import com.codeup.localscene.repositories.PostRepository;
import com.codeup.localscene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("posts")
    public String getPosts(@RequestParam Long user_id, Model model) {
        Posts post = postRepository.findById(user_id).orElse(null);
        if(post == null){
            return "redirect:/404";
        }
        model.addAttribute("post", post);
        return "posts";
    }



//    @GetMapping("/recent-activity")
//    public String showRecentActivity(Model model, Authentication authentication) {
//        // Get the current logged-in user's username or any other relevant information
//        String username = authentication.getName();
//
//        // Retrieve the posts for the current user from the database
//        List<Posts> post = postService.getPostsByUser(username);
//
//        model.addAttribute("posts", post);
//
//        return "recent-activity";
//    }

}

