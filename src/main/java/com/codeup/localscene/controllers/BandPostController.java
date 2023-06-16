package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Band;
import com.codeup.localscene.models.BandPosts;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.BandPostRepository;
import com.codeup.localscene.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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
        return "home";
    }

//    create post, saves post, redirects to list of band-posts
//    @PostMapping("/band-profile/{bandId}/band-posts/create")
//    public String createPost(@ModelAttribute("bandPost") BandPosts bandPosts, @PathVariable("bandId") Long bandId) {
//        Band band = bandRepository.getReferenceById(bandId);
//        bandPosts.setBand(band);
//        bandPostRepository.save(bandPosts);
//        return "redirect:/home";
//    }



    //delete
    @PostMapping("/band-profile/{bandId}/band-posts/{bandPostsId}/delete")
    public String deleteBandPost(@PathVariable("bandId") Long bandId,
                                 @PathVariable("bandPostsId") Long bandPostsId){
        bandPostRepository.deleteById(bandPostsId);
        return "redirect:/band-profile/{bandId}";
    }
}
