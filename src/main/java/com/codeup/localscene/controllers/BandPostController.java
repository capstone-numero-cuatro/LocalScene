package com.codeup.localscene.controllers;

import com.codeup.localscene.models.BandPosts;
import com.codeup.localscene.repositories.BandPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//@Controller
//public class BandPostController {
//
//    @Autowired
//    private BandPostRepository bandPostRepository;
//
//    //retrieves list of band posts to add to model
//    @GetMapping("/profile/{id}/band-posts")
//    public String getBandPosts(Model model) {
//        List<BandPosts> bandPosts = bandPostRepository.findAll();
//        model.addAttribute("bandPosts", bandPosts);
//        model.addAttribute("bandPost", new BandPosts());
//        return "band-posts";
//    }
//
//    //create post, saves post, redirects to list of band-posts
//    @PostMapping("/profile/{id}/band-posts/create")
//    public String createPost(@ModelAttribute("bandPost") BandPosts bandPost) {
//        bandPostRepository.save(bandPost);
//        return "redirect:/band-posts";
//    }
//
//    //delete
//    @PostMapping("/profile/{id}/band-posts/delete")
//    public String deleteBandPost(@ModelAttribute("bandPost") BandPosts bandPost){
//        bandPostRepository.delete(bandPost);
//        return "redirect:/band-posts";
//    }
//}
