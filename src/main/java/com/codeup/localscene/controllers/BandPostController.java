package com.codeup.localscene.controllers;

import com.codeup.localscene.models.BandPosts;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.BandPostRepository;
import com.codeup.localscene.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BandPostController {

    private final BandPostRepository bandPostRepository;
    private final BandRepository bandRepository;

    @Autowired
    public BandPostController(BandPostRepository bandPostRepository, BandRepository bandRepository){
        this.bandPostRepository = bandPostRepository;
        this.bandRepository = bandRepository;
    }

    //retrieves list of band posts to add to model
    @GetMapping("/band-profile/{band_id}/band-posts")
    public String getBandPosts(Model model, @PathVariable String band_id) {
        List<BandPosts> bandPosts = bandPostRepository.findAll();

        model.addAttribute("bandPosts", bandPosts);
        model.addAttribute("newBandPosts", new BandPosts());
        return "redirect:/home";
    }

//    create post, saves post, redirects to list of band-posts
    @PostMapping("/band-profile/band-posts/create")
    public String createPost(@ModelAttribute("bandPost") BandPosts bandPosts) {
        bandPostRepository.save(bandPosts);
        return "redirect:/home";
    }

//    @PostMapping("/profile/posts/create")
//    public String createPost(@ModelAttribute Posts posts) {
//
//        Band loggedInUser =
//                (Band) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        loggedInUser = bandRepository.findByUsername(loggedInUser);
//
//        posts.setUser(loggedInUser);
//
////        System.out.println("posts.getUser_id().getUsername() = " + posts.getUser().getUsername());
//
//        postRepository.save(posts);
//
//        return "redirect:/band-profile/" + loggedInUser.getId();
//    }

    //delete
    @PostMapping("/profile/{id}/band-posts/delete")
    public String deleteBandPost(@ModelAttribute("bandPost") BandPosts bandPosts){
        bandPostRepository.delete(bandPosts);
        return "redirect:/band-posts";
    }
}
